package dev.guillem.githubbrowserlab.domain.interactor

import dev.guillem.githubbrowserlab.domain.UsersRepository
import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import dev.guillem.githubbrowserlab.factory.UserFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetUsersTest {
    private lateinit var getUsers: GetUsers

    private val usersRepository: UsersRepository = mock()
    private val threadExecutorMock: ThreadExecutor = mock()
    private val postExecutionThreadMock: PostExecutionThread = mock()

    @Before
    fun setUp() {
        getUsers = GetUsers(
            usersRepository,
            threadExecutorMock,
            postExecutionThreadMock
        )
    }

    @Test
    fun `Build use case observable calls repository`() {
        getUsers.buildSingleUseCaseObservable()

        verify(usersRepository).getUsersFromCSV()
    }

    @Test
    fun `Build use case observable completes`() {
        whenever(usersRepository.getUsersFromCSV()).thenReturn(Single.just(SOME_USERS))

        val testObserver = getUsers.buildSingleUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun `Build use case observable returns data`() {
        whenever(usersRepository.getUsersFromCSV()).thenReturn(Single.just(SOME_USERS))

        val testObserver = getUsers.buildSingleUseCaseObservable().test()
        testObserver.assertValue(SOME_USERS)
    }

    companion object {
        private val SOME_USERS = UserFactory.makeUserList(2)
    }
}