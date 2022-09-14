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
import dev.guillem.githubbrowserlab.domain.entity.User

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var sourceBinding: MainFragmentBinding? = null
    private val binding get() = sourceBinding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

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
            userAdapter = UserAdapter(
                mutableListOf(),
            )
            adapter = userAdapter
        }
    }

    private fun configureSwipeToRefresh() {
        binding.viewSwipeToRefresh.apply {
            setOnRefreshListener { viewModel.onViewReady() }
        }
    }

    private fun renderUi(viewState: MainViewState) {
        when (viewState) {
            MainViewState.Loading -> setupLoadingState()
            MainViewState.Error -> setupErrorState()
            is MainViewState.Content -> setupSuccessState(viewState.users)
        }
    }

    private fun setupLoadingState() {
        binding.viewSwipeToRefresh.isRefreshing = true
    }

    private fun setupErrorState() {
        binding.viewSwipeToRefresh.isRefreshing = false
    }

    private fun setupSuccessState(users: List<User>) {
        binding.viewSwipeToRefresh.isRefreshing = false
        userAdapter.update(users)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}