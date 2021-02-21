package dev.guillem.githubbrowserlab.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.guillem.githubbrowserlab.databinding.MainFragmentBinding

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var sourceBinding: MainFragmentBinding? = null
    private val binding get() = sourceBinding!!
    private val viewModel: MainViewModel by viewModels()

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

    companion object {
        fun newInstance() = MainFragment()
    }

}