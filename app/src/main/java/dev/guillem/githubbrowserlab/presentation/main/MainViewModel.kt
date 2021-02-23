package dev.guillem.githubbrowserlab.presentation.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.guillem.githubbrowserlab.domain.entity.Repository
import dev.guillem.githubbrowserlab.domain.interactor.GetCompanyRepos
import dev.guillem.githubbrowserlab.presentation.mapper.RepositoryMapper
import dev.guillem.githubbrowserlab.presentation.model.RepositoryView
import dev.guillem.githubbrowserlab.presentation.tools.browser.Browser
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryMapper: RepositoryMapper,
    private val getCompanyReposUseCase: GetCompanyRepos,
    private val browser: Browser,
) : ViewModel(), RepositoryClickListener {
    val viewState = MutableLiveData<MainViewState>()

    fun onViewReady() {
        viewState.value = MainViewState.Loading
        getCompanyReposUseCase.execute(GetCompanyReposObserver())
    }

    public override fun onCleared() {
        super.onCleared()
        getCompanyReposUseCase.clear()
    }

    private fun handleSuccess(repositories: List<Repository>) {
        val repositoriesView = repositories.map { listItem ->
            repositoryMapper.mapToView(listItem)
        }
        viewState.value = MainViewState.Content(repositoriesView)
    }

    private fun handleError() {
        viewState.value = MainViewState.Error
    }

    private inner class GetCompanyReposObserver : DisposableSingleObserver<List<Repository>>() {
        override fun onSuccess(result: List<Repository>) {
            handleSuccess(result)
        }

        override fun onError(throwable: Throwable) {
            handleError()
        }
    }

    override fun onRepositoryLongClick(repositoryView: RepositoryView) {
        viewState.value = MainViewState.RepositoryClicked(repositoryView)
    }

    fun onLearnMoreRepositoryClick(context: Context, repositoryView: RepositoryView) {
        browser.launch(context, repositoryView.htmlUrl)
    }

    fun onLearnMoreOwnerClick(context: Context, repositoryView: RepositoryView) {
        browser.launch(context, repositoryView.owner.htmlUrl)
    }
}