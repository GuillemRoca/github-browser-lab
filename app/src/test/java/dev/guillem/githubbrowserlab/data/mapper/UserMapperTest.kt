package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.factory.UserFactory
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UserMapperTest {
    private lateinit var userMapper: UserMapper

    @Before
    fun setUp() {
        userMapper = UserMapper()
    }

    @Test
    fun `Should map from entity data`() {
        val expectedUser = UserFactory.makeUser()
        val userEntity = UserFactory.makeUserEntity()

        val user = userMapper.mapFromEntity(userEntity)

        assertEquals(expectedUser, user)
    }

    @Test
    fun `Should map to entity`() {
        val expectedUserEntity = UserFactory.makeUserEntity()
        val user = UserFactory.makeUser()

        val userEntity = userMapper.mapToEntity(user)

        assertEquals(expectedUserEntity, userEntity)
    }
}