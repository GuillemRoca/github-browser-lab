package dev.guillem.githubbrowserlab.presentation.main

import dev.guillem.githubbrowserlab.presentation.model.RepositoryView

sealed class MainViewState {
    object Loading : MainViewState()
    object Error : MainViewState()
    data class Success(val repositories: List<RepositoryView>) : MainViewState()
    data class RepositoryClicked(val repositoryView: RepositoryView) : MainViewState()
}
