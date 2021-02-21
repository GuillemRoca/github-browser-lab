package dev.guillem.githubbrowserlab.presentation.main

import dev.guillem.githubbrowserlab.presentation.model.RepositoryView

data class MainViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val repositories: List<RepositoryView> = emptyList(),
    val lastClickedRepository: RepositoryView? = null,
)
