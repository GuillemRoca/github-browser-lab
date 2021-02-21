package dev.guillem.githubbrowserlab.factory

import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.data.model.RepositoryEntity
import dev.guillem.githubbrowserlab.domain.entity.Repository
import dev.guillem.githubbrowserlab.presentation.model.RepositoryView

object RepositoryFactory {
    private const val SOME_ID = 3222
    private const val SOME_NAME = "media"
    private const val SOME_HTML_URL = "https://github.com/github/media"
    private const val SOME_DESCRIPTION = "Media files for use in your GitHub integration projects"
    private const val SOME_IS_FORKED = false
    private const val SOME_BACKGROUND_COLOR = R.color.white_transparent
    private val SOME_OWNER_ENTITY = OwnerFactory.makeOwnerEntity()
    private val SOME_OWNER = OwnerFactory.makeOwner()
    private val SOME_OWNER_VIEW = OwnerFactory.makeOwnerView()

    fun makeRepositoryEntity(): RepositoryEntity =
        RepositoryEntity(
            id = SOME_ID,
            name = SOME_NAME,
            owner = SOME_OWNER_ENTITY,
            htmlUrl = SOME_HTML_URL,
            description = SOME_DESCRIPTION,
            fork = SOME_IS_FORKED,
        )

    fun makeRepository(): Repository =
        Repository(
            id = SOME_ID,
            name = SOME_NAME,
            owner = SOME_OWNER,
            htmlUrl = SOME_HTML_URL,
            description = SOME_DESCRIPTION,
            fork = SOME_IS_FORKED,
        )

    fun makeRepositoryView(): RepositoryView =
        RepositoryView(
            id = SOME_ID,
            name = SOME_NAME,
            owner = SOME_OWNER_VIEW,
            htmlUrl = SOME_HTML_URL,
            description = SOME_DESCRIPTION,
            backgroundColor = SOME_BACKGROUND_COLOR,
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