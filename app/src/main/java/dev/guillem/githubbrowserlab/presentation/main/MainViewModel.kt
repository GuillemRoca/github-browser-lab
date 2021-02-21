package dev.guillem.githubbrowserlab.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.guillem.githubbrowserlab.domain.interactor.GetCompanyRepos
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCompanyReposUseCase: GetCompanyRepos
) : ViewModel() {
    // TODO: Implement the ViewModel
}