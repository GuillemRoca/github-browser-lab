package dev.guillem.githubbrowserlab.presentation.mapper

import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.domain.entity.Repository
import dev.guillem.githubbrowserlab.presentation.model.RepositoryView
import javax.inject.Inject

class RepositoryMapper @Inject constructor(private val ownerMapper: OwnerMapper) :
    Mapper<RepositoryView, Repository> {
    override fun mapToView(type: Repository): RepositoryView =
        RepositoryView(
            id = type.id,
            name = type.name,
            owner = ownerMapper.mapToView(type.owner),
            htmlUrl = type.htmlUrl,
            description = type.description,
            backgroundColor = if (type.fork) R.color.teal_200 else R.color.white_transparent,
        )
}