package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.data.model.RepositoryEntity
import dev.guillem.githubbrowserlab.domain.entity.Repository
import javax.inject.Inject

class RepositoryMapper @Inject constructor(private val ownerMapper: OwnerMapper) :
    Mapper<RepositoryEntity, Repository> {
    override fun mapFromEntity(type: RepositoryEntity): Repository =
        Repository(
            id = type.id,
            name = type.name,
            owner = ownerMapper.mapFromEntity(type.owner),
            htmlUrl = type.htmlUrl,
            description = type.description,
            fork = type.fork
        )

    override fun mapToEntity(type: Repository): RepositoryEntity =
        RepositoryEntity(
            id = type.id,
            name = type.name,
            owner = ownerMapper.mapToEntity(type.owner),
            htmlUrl = type.htmlUrl,
            description = type.description,
            fork = type.fork
        )
}