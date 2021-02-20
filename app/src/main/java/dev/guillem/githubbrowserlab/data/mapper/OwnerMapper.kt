package dev.guillem.githubbrowserlab.data.mapper

import dev.guillem.githubbrowserlab.data.model.OwnerEntity
import dev.guillem.githubbrowserlab.domain.entity.Owner
import javax.inject.Inject

class OwnerMapper @Inject constructor() : Mapper<OwnerEntity, Owner> {
    override fun mapFromEntity(type: OwnerEntity): Owner =
        Owner(
            login = type.login,
            id = type.id,
            avatarUrl = type.avatarUrl,
            htmlUrl = type.htmlUrl
        )

    override fun mapToEntity(type: Owner): OwnerEntity =
        OwnerEntity(
            login = type.login,
            id = type.id,
            avatarUrl = type.avatarUrl,
            htmlUrl = type.htmlUrl
        )
}