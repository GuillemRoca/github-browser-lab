package dev.guillem.githubbrowserlab.presentation.main

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.guillem.githubbrowserlab.domain.entity.Repository
import dev.guillem.githubbrowserlab.domain.interactor.GetCompanyRepos
import dev.guillem.githubbrowserlab.factory.RepositoryFactory
import dev.guillem.githubbrowserlab.presentation.mapper.RepositoryMapper
import dev.guillem.githubbrowserlab.presentation.tools.browser.Browser
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals

class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private val repositoryMapperMock: RepositoryMapper = mock()
    private val getCompanyReposUseCaseMock: GetCompanyRepos = mock()
    private val browserMock: Browser = mock()
    private val contextMock: Context = mock()
    private val getCompanyReposUseObserverCaptor =
        argumentCaptor<DisposableSingleObserver<List<Repository>>>()

    @Before
    fun setUp() {
        viewModel = MainViewModel(repositoryMapperMock, getCompanyReposUseCaseMock, browserMock)
    }

    @Test
    fun `Should set viewState with state Loading when view ready`() {
        val viewStateExpected = MainViewState.Loading

        viewModel.onViewReady()

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    @Test
    fun `Should execute getCompanyRepos use case when view ready`() {
        viewModel.onViewReady()

        verify(getCompanyReposUseCaseMock).execute(getCompanyReposUseObserverCaptor.capture())
    }

    @Test
    fun `Should clear getCompanyRepos use case when view ready`() {
        viewModel.onCleared()

        verify(getCompanyReposUseCaseMock).clear()
    }

    @Test
    fun `Should render content view state when success result of get company repos use case`() {
        viewModel.onViewReady()
        verify(getCompanyReposUseCaseMock).execute(getCompanyReposUseObserverCaptor.capture())
        whenever(repositoryMapperMock.mapToView(SOME_REPOSITORY)).thenReturn(SOME_REPOSITORY_VIEW)
        val viewStateExpected = MainViewState.Content(SOME_REPOSITORIES_VIEW)

        getCompanyReposUseObserverCaptor.firstValue.onSuccess(SOME_REPOSITORIES)

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    @Test
    fun `Should render error view state when error result of get company repos use case`() {
        viewModel.onViewReady()
        verify(getCompanyReposUseCaseMock).execute(getCompanyReposUseObserverCaptor.capture())
        val viewStateExpected = MainViewState.Error

        getCompanyReposUseObserverCaptor.firstValue.onError(Throwable())

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    @Test
    fun `Should render repository click view state when on repository long click`() {
        val viewStateExpected = MainViewState.RepositoryClicked(SOME_REPOSITORY_VIEW)

        viewModel.onRepositoryLongClick(SOME_REPOSITORY_VIEW)

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    @Test
    fun `Should launch repository browser url when on learn more repository click`() {
        val urlExpected = SOME_REPOSITORY_VIEW.htmlUrl

        viewModel.onLearnMoreRepositoryClick(contextMock, SOME_REPOSITORY_VIEW)

        verify(browserMock).launch(eq(contextMock), eq(urlExpected))
    }

    @Test
    fun `Should launch owner browser url when on learn more owner click`() {
        val urlExpected = SOME_REPOSITORY_VIEW.owner.htmlUrl

        viewModel.onLearnMoreOwnerClick(contextMock, SOME_REPOSITORY_VIEW)

        verify(browserMock).launch(eq(contextMock), eq(urlExpected))
    }

    companion object {
        private val SOME_REPOSITORY = RepositoryFactory.makeRepository()
        private val SOME_REPOSITORIES = listOf(SOME_REPOSITORY)
        private val SOME_REPOSITORY_VIEW = RepositoryFactory.makeRepositoryView()
        private val SOME_REPOSITORIES_VIEW = listOf(SOME_REPOSITORY_VIEW)
    }
}