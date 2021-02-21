package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.factory.OwnerFactory
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OwnerMapperTest {
    private lateinit var ownerMapper: OwnerMapper

    @Before
    fun setUp() {
        ownerMapper = OwnerMapper()
    }

    @Test
    fun `Should map from entity data`() {
        val expectedOwner = OwnerFactory.makeOwner()
        val ownerEntity = OwnerFactory.makeOwnerEntity()

        val owner = ownerMapper.mapFromEntity(ownerEntity)

        assertEquals(owner, expectedOwner)
    }

    @Test
    fun `Should map to entity`() {
        val expectedOwnerEntity = OwnerFactory.makeOwnerEntity()
        val owner = OwnerFactory.makeOwner()

        val ownerEntity = ownerMapper.mapToEntity(owner)

        assertEquals(ownerEntity, expectedOwnerEntity)
    }
}