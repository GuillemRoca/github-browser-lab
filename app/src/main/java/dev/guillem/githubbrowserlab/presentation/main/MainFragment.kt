package dev.guillem.githubbrowserlab.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.databinding.MainFragmentBinding
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
        viewModel.viewState.observe(viewLifecycleOwner, {
            renderUi(it)
        })
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
                context.getColorFromAttr(R.attr.colorPrimary),
                context.getColorFromAttr(R.attr.colorSecondary)
            )
            setOnRefreshListener { viewModel.onViewReady() }
        }
    }

    private fun renderUi(viewState: MainViewState) {
        binding.viewSwipeToRefresh.isRefreshing = viewState.isLoading
        repositoryAdapter.update(viewState.repositories)
        viewState.lastClickedRepository?.let {
            alertDialogFactory.showMoreInfoRepository(
                context = requireContext(),
                learnMoreRepositoryClickListener = { _, _ ->
                    viewModel.onLearnMoreRepositoryClick(requireContext(), it)
                },
                learnMoreOwnerClickListener = { _, _ ->
                    viewModel.onLearnMoreOwnerClick(requireContext(), it)
                }
            )
        }
        if (viewState.isError) {
            alertDialogFactory.showError(requireContext())
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}