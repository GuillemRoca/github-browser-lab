package dev.guillem.githubbrowserlab.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.guillem.githubbrowserlab.domain.entity.User
import dev.guillem.githubbrowserlab.domain.interactor.GetUsers
import dev.guillem.githubbrowserlab.factory.UserFactory
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
    private val getUsersMock: GetUsers = mock()
    private val getUsersUseObserverCaptor =
        argumentCaptor<DisposableSingleObserver<List<User>>>()

    @Before
    fun setUp() {
        viewModel = MainViewModel(getUsersMock)
    }

    @Test
    fun `Should set viewState with state Loading when view ready`() {
        val viewStateExpected = MainViewState.Loading

        viewModel.onViewReady()

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    @Test
    fun `Should execute get users use case when view ready`() {
        viewModel.onViewReady()

        verify(getUsersMock).execute(getUsersUseObserverCaptor.capture())
    }

    @Test
    fun `Should clear get users use case when view ready`() {
        viewModel.onCleared()

        verify(getUsersMock).clear()
    }

    @Test
    fun `Should render content view state when success result of get users use case`() {
        viewModel.onViewReady()
        verify(getUsersMock).execute(getUsersUseObserverCaptor.capture())
        val viewStateExpected = MainViewState.Content(SOME_USERS)

        getUsersUseObserverCaptor.firstValue.onSuccess(SOME_USERS)

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    @Test
    fun `Should render error view state when error result of get users use case`() {
        viewModel.onViewReady()
        verify(getUsersMock).execute(getUsersUseObserverCaptor.capture())
        val viewStateExpected = MainViewState.Error

        getUsersUseObserverCaptor.firstValue.onError(Throwable())

        assertEquals(viewModel.viewState.value, viewStateExpected)
    }

    companion object {
        private val SOME_USERS = listOf(UserFactory.makeUser())
    }
}