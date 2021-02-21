package dev.guillem.githubbrowserlab.presentation.mapper

import dev.guillem.githubbrowserlab.factory.OwnerFactory
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class OwnerMapperTest {
    private lateinit var ownerMapper: OwnerMapper

    @Before
    fun setUp() {
        ownerMapper = OwnerMapper()
    }

    @Test
    fun `Should map to view`() {
        val expectedOwnerView = OwnerFactory.makeOwnerView()
        val owner = OwnerFactory.makeOwner()

        val ownerView = ownerMapper.mapToView(owner)

        assertEquals(ownerView, expectedOwnerView)
    }
}