package dev.guillem.githubbrowserlab.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.guillem.githubbrowserlab.data.factory.RepositoryFactory
import dev.guillem.githubbrowserlab.data.mapper.RepositoryMapper
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ReposDataRepositoryTest {
    private lateinit var reposDataRepository: ReposDataRepository
    private val apiServiceMock: ApiService = mock()
    private val repositoryMapperMock: RepositoryMapper = mock()

    @Before
    fun setUp() {
        reposDataRepository = ReposDataRepository(apiServiceMock, repositoryMapperMock)
    }

    @Test
    fun `Should get company repos from api service`() {
        val repositoriesEntities = RepositoryFactory.makeRepositoryEntityList(2)
        whenever(apiServiceMock.getCompanyRepos()).thenReturn(Single.just(repositoriesEntities))

        val testObserver = reposDataRepository.getCompanyRepos().test()

        verify(apiServiceMock).getCompanyRepos()
        testObserver.assertComplete()
    }

    @Test
    fun `Should get company repos from api service and return data`() {
        val repositories = RepositoryFactory.makeRepositoryList(2)
        val repositoriesEntities = RepositoryFactory.makeRepositoryEntityList(2)
        repositories.forEachIndexed { index, repository ->
            whenever(repositoryMapperMock.mapFromEntity(repositoriesEntities[index]))
                .thenReturn(repository)
        }
        whenever(apiServiceMock.getCompanyRepos()).thenReturn(Single.just(repositoriesEntities))

        val testObserver = reposDataRepository.getCompanyRepos().test()

        verify(apiServiceMock).getCompanyRepos()
        testObserver.assertValue(repositories)
    }
}