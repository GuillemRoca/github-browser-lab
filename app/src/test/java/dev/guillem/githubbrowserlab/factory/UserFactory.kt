package dev.guillem.githubbrowserlab.factory

import dev.guillem.githubbrowserlab.data.model.UserEntity
import dev.guillem.githubbrowserlab.domain.entity.User

object UserFactory {
    private const val NAME = "Shelby Macias\t\t\tet@eratvolutpat.ca"
    private const val ADDRESS = "3027 Lorem St.|Kokomo|Hertfordshire|L9T 3D5|Finland"
    private const val PHONE = "1 66 890 3865-9584"
    private const val EMAIL = "et@eratvolutpat.ca"

    fun makeUserEntity(): UserEntity =
        UserEntity(
            name = NAME,
            address = ADDRESS,
            phone = PHONE,
            email = EMAIL
        )

    fun makeUser(): User =
        User(
            name = NAME,
            address = ADDRESS,
            phone = PHONE,
            email = EMAIL
        )

    fun makeUserEntityList(count: Int): List<UserEntity> {
        val userEntities = mutableListOf<UserEntity>()
        repeat(count) {
            userEntities.add(makeUserEntity())
        }
        return userEntities
    }

    fun makeUserList(count: Int): List<User> {
        val users = mutableListOf<User>()
        repeat(count) {
            users.add(makeUser())
        }
        return users
    }
}