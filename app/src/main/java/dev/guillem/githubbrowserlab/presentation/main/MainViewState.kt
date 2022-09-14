package dev.guillem.githubbrowserlab.presentation.main

import dev.guillem.githubbrowserlab.domain.entity.User

sealed class MainViewState {
    object Loading : MainViewState()
    object Error : MainViewState()
    data class Content(val users: List<User>) : MainViewState()
}
