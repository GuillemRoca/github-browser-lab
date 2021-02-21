package dev.guillem.githubbrowserlab.domain.interactor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.guillem.githubbrowserlab.factory.RepositoryFactory
import dev.guillem.githubbrowserlab.domain.ReposRepository
import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import io.reactivex.Single
import org.junit.Before

import org.junit.Test

class GetCompanyReposTest {
    private lateinit var getCompanyRepos: GetCompanyRepos

    private val reposRepository: ReposRepository = mock()
    private val threadExecutorMock: ThreadExecutor = mock()
    private val postExecutionThreadMock: PostExecutionThread = mock()

    @Before
    fun setUp() {
        getCompanyRepos = GetCompanyRepos(
            reposRepository,
            threadExecutorMock,
            postExecutionThreadMock
        )
    }

    @Test
    fun `Build use case observable calls repository`() {
        getCompanyRepos.buildSingleUseCaseObservable()

        verify(reposRepository).getCompanyRepos()
    }

    @Test
    fun `Build use case observable completes`() {
        whenever(reposRepository.getCompanyRepos()).thenReturn(Single.just(SOME_REPOSITORIES))

        val testObserver = getCompanyRepos.buildSingleUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun `Build use case observable returns data`() {
        whenever(reposRepository.getCompanyRepos()).thenReturn(Single.just(SOME_REPOSITORIES))

        val testObserver = getCompanyRepos.buildSingleUseCaseObservable().test()
        testObserver.assertValue(SOME_REPOSITORIES)
    }

    companion object {
        private val SOME_REPOSITORIES = RepositoryFactory.makeRepositoryList(2)
    }
}