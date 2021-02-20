package dev.guillem.githubbrowserlab.data.factory

import dev.guillem.githubbrowserlab.data.entity.OwnerEntity
import dev.guillem.githubbrowserlab.data.factory.DataFactory.randomInt
import dev.guillem.githubbrowserlab.data.factory.DataFactory.randomUuid
import dev.guillem.githubbrowserlab.domain.entity.Owner

object OwnerFactory {
    fun makeOwnerEntity(): OwnerEntity =
        OwnerEntity(
            login = randomUuid(),
            id = randomInt(),
            avatarUrl = randomUuid(),
            htmlUrl = randomUuid(),
        )

    fun makeOwner(): Owner =
        Owner(
            login = randomUuid(),
            id = randomInt(),
            avatarUrl = randomUuid(),
            htmlUrl = randomUuid(),
        )
}