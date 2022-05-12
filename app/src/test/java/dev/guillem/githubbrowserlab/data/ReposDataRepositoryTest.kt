package dev.guillem.githubbrowserlab.data

import dev.guillem.githubbrowserlab.data.mapper.RepositoryMapper
import dev.guillem.githubbrowserlab.factory.RepositoryFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

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