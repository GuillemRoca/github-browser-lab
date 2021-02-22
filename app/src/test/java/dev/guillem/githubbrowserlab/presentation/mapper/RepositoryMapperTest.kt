package dev.guillem.githubbrowserlab.presentation.mapper

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.factory.RepositoryFactory
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RepositoryMapperTest {
    private lateinit var repositoryMapper: RepositoryMapper
    private val ownerMapperMock: OwnerMapper = mock()

    @Before
    fun setUp() {
        repositoryMapper = RepositoryMapper(ownerMapperMock)
    }

    @Test
    fun `Should background color be white when map to view with repository not forked`() {
        val expectedRepositoryView = RepositoryFactory.makeRepositoryView()
        val repository = RepositoryFactory.makeRepository()
        whenever(ownerMapperMock.mapToView(repository.owner)).thenReturn(expectedRepositoryView.owner)

        val repositoryView = repositoryMapper.mapToView(repository)

        assertEquals(repositoryView, expectedRepositoryView)
    }

    @Test
    fun `Should background color be secondary when when map to view with repository forked`() {
        val expectedRepositoryView = RepositoryFactory
            .makeRepositoryView()
            .copy(backgroundAttrColor = R.attr.colorSecondary)
        val repository = RepositoryFactory
            .makeRepository()
            .copy(fork = true)
        whenever(ownerMapperMock.mapToView(repository.owner)).thenReturn(expectedRepositoryView.owner)

        val repositoryView = repositoryMapper.mapToView(repository)

        assertEquals(repositoryView, expectedRepositoryView)
    }
}