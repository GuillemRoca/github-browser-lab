package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.data.model.UserEntity
import dev.guillem.githubbrowserlab.domain.entity.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserEntity, User> {
    fun mapFromArray(type: Array<String>) =
        UserEntity(
            name = type[0],
            address = type[1],
            phone = type[2],
            email = type[3]
        )

    override fun mapFromEntity(type: UserEntity): User =
        User(
            name = type.name,
            address = type.address,
            phone = type.phone,
            email = type.email
        )

    override fun mapToEntity(type: User): UserEntity =
        UserEntity(
            name = type.name,
            address = type.address,
            phone = type.phone,
            email = type.email
        )
}