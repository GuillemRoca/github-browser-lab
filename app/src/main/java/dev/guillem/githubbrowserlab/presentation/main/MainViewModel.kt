package dev.guillem.githubbrowserlab.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.guillem.githubbrowserlab.domain.entity.User
import dev.guillem.githubbrowserlab.domain.interactor.GetUsers
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsers: GetUsers,
) : ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState>
        get() = _viewState

    fun onViewReady() {
        _viewState.value = MainViewState.Loading
        getUsers.execute(GetUsersReposObserver())
    }

    public override fun onCleared() {
        super.onCleared()
        getUsers.clear()
    }

    private fun handleSuccess(users: List<User>) {
        _viewState.value = MainViewState.Content(users)
    }

    private fun handleError() {
        _viewState.value = MainViewState.Error
    }

    private inner class GetUsersReposObserver : DisposableSingleObserver<List<User>>() {
        override fun onSuccess(result: List<User>) {
            handleSuccess(result)
        }

        override fun onError(throwable: Throwable) {
            handleError()
        }
    }
}