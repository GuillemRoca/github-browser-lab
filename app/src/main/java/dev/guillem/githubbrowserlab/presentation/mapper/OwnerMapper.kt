package dev.guillem.githubbrowserlab.presentation.mapper

import dev.guillem.githubbrowserlab.domain.entity.Owner
import dev.guillem.githubbrowserlab.presentation.model.OwnerView
import javax.inject.Inject

class OwnerMapper @Inject constructor() : Mapper<OwnerView, Owner> {
    override fun mapToView(type: Owner): OwnerView =
        OwnerView(
            login = type.login,
            id = type.id,
            avatarUrl = type.avatarUrl,
            htmlUrl = type.htmlUrl,
        )
}