package dev.guillem.githubbrowserlab.data.factory

import dev.guillem.githubbrowserlab.data.model.RepositoryEntity
import dev.guillem.githubbrowserlab.data.factory.DataFactory.randomBoolean
import dev.guillem.githubbrowserlab.data.factory.DataFactory.randomInt
import dev.guillem.githubbrowserlab.data.factory.DataFactory.randomUuid
import dev.guillem.githubbrowserlab.domain.entity.Repository

object RepositoryFactory {
    fun makeRepositoryEntity(): RepositoryEntity =
        RepositoryEntity(
            id = randomInt(),
            name = randomUuid(),
            owner = OwnerFactory.makeOwnerEntity(),
            htmlUrl = randomUuid(),
            description = randomUuid(),
            fork = randomBoolean(),
        )

    fun makeRepository(): Repository =
        Repository(
            id = randomInt(),
            name = randomUuid(),
            owner = OwnerFactory.makeOwner(),
            htmlUrl = randomUuid(),
            description = randomUuid(),
            fork = randomBoolean(),
        )

    fun makeRepositoryEntityList(count: Int): List<RepositoryEntity> {
        val repositoryEntities = mutableListOf<RepositoryEntity>()
        repeat(count) {
            repositoryEntities.add(makeRepositoryEntity())
        }
        return repositoryEntities
    }

    fun makeRepositoryList(count: Int): List<Repository> {
        val repositories = mutableListOf<Repository>()
        repeat(count) {
            repositories.add(makeRepository())
        }
        return repositories
    }
}