package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.data.model.OwnerEntity
import dev.guillem.githubbrowserlab.data.factory.OwnerFactory
import dev.guillem.githubbrowserlab.domain.entity.Owner
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
        val ownerEntity = OwnerFactory.makeOwnerEntity()
        val owner = ownerMapper.mapFromEntity(ownerEntity)

        assertOwnerDataEquality(ownerEntity, owner)
    }

    @Test
    fun `Should map to entity data`() {
        val owner = OwnerFactory.makeOwner()
        val ownerEntity = ownerMapper.mapToEntity(owner)

        assertOwnerDataEquality(ownerEntity, owner)
    }

    private fun assertOwnerDataEquality(ownerEntity: OwnerEntity, owner: Owner) {
        assertEquals(ownerEntity.login, owner.login)
        assertEquals(ownerEntity.id, owner.id)
        assertEquals(ownerEntity.avatarUrl, ownerEntity.avatarUrl)
        assertEquals(ownerEntity.htmlUrl, ownerEntity.htmlUrl)
    }
}