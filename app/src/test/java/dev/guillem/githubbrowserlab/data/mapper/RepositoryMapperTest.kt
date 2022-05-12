package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.factory.RepositoryFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class RepositoryMapperTest {
    private lateinit var repositoryMapper: RepositoryMapper
    private val ownerMapperMock: OwnerMapper = mock()

    @Before
    fun setUp() {
        repositoryMapper = RepositoryMapper(ownerMapperMock)
    }

    @Test
    fun `Should map from entity`() {
        val expectedRepository = RepositoryFactory.makeRepository()
        val repositoryEntity = RepositoryFactory.makeRepositoryEntity()
        whenever(ownerMapperMock.mapFromEntity(repositoryEntity.owner)).thenReturn(
            expectedRepository.owner
        )

        val repository = repositoryMapper.mapFromEntity(repositoryEntity)

        assertEquals(repository, expectedRepository)
    }

    @Test
    fun `Should map to entity`() {
        val expectedRepositoryEntity = RepositoryFactory.makeRepositoryEntity()
        val repository = RepositoryFactory.makeRepository()
        whenever(ownerMapperMock.mapToEntity(repository.owner)).thenReturn(expectedRepositoryEntity.owner)

        val repositoryEntity = repositoryMapper.mapToEntity(repository)

        assertEquals(repositoryEntity, expectedRepositoryEntity)
    }
}