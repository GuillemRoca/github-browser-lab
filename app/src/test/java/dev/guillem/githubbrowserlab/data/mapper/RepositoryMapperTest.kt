package dev.guillem.githubbrowserlab.data.mapper

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dev.guillem.githubbrowserlab.data.entity.RepositoryEntity
import dev.guillem.githubbrowserlab.data.factory.OwnerFactory
import dev.guillem.githubbrowserlab.data.factory.RepositoryFactory
import dev.guillem.githubbrowserlab.domain.entity.Repository
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
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
    fun `Should map from entity data`() {
        val repositoryEntity = RepositoryFactory.makeRepositoryEntity()
        whenever(ownerMapperMock.mapFromEntity(repositoryEntity.owner)).thenReturn(OwnerFactory.makeOwner())

        val repository = repositoryMapper.mapFromEntity(repositoryEntity)

        assertRepositoryDataEquality(repositoryEntity, repository)
    }

    @Test
    fun `Should map to entity data`() {
        val repository = RepositoryFactory.makeRepository()
        whenever(ownerMapperMock.mapToEntity(repository.owner)).thenReturn(OwnerFactory.makeOwnerEntity())

        val repositoryEntity = repositoryMapper.mapToEntity(repository)

        assertRepositoryDataEquality(repositoryEntity, repository)
    }

    private fun assertRepositoryDataEquality(
        repositoryEntity: RepositoryEntity,
        repository: Repository
    ) {
        assertEquals(repositoryEntity.id, repository.id)
        assertEquals(repositoryEntity.name, repository.name)
        assertEquals(repositoryEntity.htmlUrl, repository.htmlUrl)
        assertEquals(repositoryEntity.description, repository.description)
        assertEquals(repositoryEntity.fork, repository.fork)
    }
}