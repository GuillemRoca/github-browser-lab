package dev.guillem.githubbrowserlab.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.guillem.githubbrowserlab.databinding.MainFragmentBinding
import dev.guillem.githubbrowserlab.presentation.model.RepositoryView
import dev.guillem.githubbrowserlab.presentation.tools.dialog.AlertDialogFactory
import dev.guillem.githubbrowserlab.presentation.tools.extensions.getColorFromAttr
import dev.guillem.githubbrowserlab.presentation.tools.imageloader.ImageLoader
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var alertDialogFactory: AlertDialogFactory
    private var sourceBinding: MainFragmentBinding? = null
    private val binding get() = sourceBinding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sourceBinding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sourceBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUi()
        viewModel.viewState.observe(viewLifecycleOwner) {
            renderUi(it)
        }
        viewModel.onViewReady()
    }

    private fun configureUi() {
        configureRecyclerView()
        configureSwipeToRefresh()
    }

    private fun configureRecyclerView() {
        binding.recyclerviewRepositories.apply {
            layoutManager = LinearLayoutManager(context)
            repositoryAdapter = RepositoryAdapter(
                mutableListOf(),
                viewModel as RepositoryClickListener,
                imageLoader,
            )
            adapter = repositoryAdapter
        }
    }

    private fun configureSwipeToRefresh() {
        binding.viewSwipeToRefresh.apply {
            setColorSchemeColors(
                context.getColorFromAttr(androidx.appcompat.R.attr.colorPrimary),
                context.getColorFromAttr(com.google.android.material.R.attr.colorSecondary)
            )
            setOnRefreshListener { viewModel.onViewReady() }
        }
    }

    private fun renderUi(viewState: MainViewState) {
        when (viewState) {
            MainViewState.Loading -> setupLoadingState()
            MainViewState.Error -> setupErrorState()
            is MainViewState.Content -> setupSuccessState(viewState.repositories)
            is MainViewState.RepositoryClicked -> setupRepositoryClickedState(viewState.repositoryView)
        }
    }

    private fun setupLoadingState() {
        binding.viewSwipeToRefresh.isRefreshing = true
    }

    private fun setupErrorState() {
        binding.viewSwipeToRefresh.isRefreshing = false
        alertDialogFactory.showError(requireContext())
    }

    private fun setupSuccessState(repositories: List<RepositoryView>) {
        binding.viewSwipeToRefresh.isRefreshing = false
        repositoryAdapter.update(repositories)
    }

    private fun setupRepositoryClickedState(repositoryView: RepositoryView) {
        alertDialogFactory.showMoreInfoRepository(
            context = requireContext(),
            learnMoreRepositoryClickListener = { _, _ ->
                viewModel.onLearnMoreRepositoryClick(requireContext(), repositoryView)
            },
            learnMoreOwnerClickListener = { _, _ ->
                viewModel.onLearnMoreOwnerClick(requireContext(), repositoryView)
            }
        )
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}