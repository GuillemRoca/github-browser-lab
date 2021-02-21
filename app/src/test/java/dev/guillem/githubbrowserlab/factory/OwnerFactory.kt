package dev.guillem.githubbrowserlab.factory

import dev.guillem.githubbrowserlab.data.model.OwnerEntity
import dev.guillem.githubbrowserlab.domain.entity.Owner
import dev.guillem.githubbrowserlab.presentation.model.OwnerView

object OwnerFactory {
    private const val LOGIN = "github"
    private const val ID = 9919
    private const val AVATAR_URL = "https://avatars.githubusercontent.com/u/9919?v=4"
    private const val HTML_URL = "https://github.com/github"

    fun makeOwnerEntity(): OwnerEntity =
        OwnerEntity(
            login = LOGIN,
            id = ID,
            avatarUrl = AVATAR_URL,
            htmlUrl = HTML_URL,
        )

    fun makeOwner(): Owner =
        Owner(
            login = LOGIN,
            id = ID,
            avatarUrl = AVATAR_URL,
            htmlUrl = HTML_URL,
        )

    fun makeOwnerView(): OwnerView =
        OwnerView(
            login = LOGIN,
            id = ID,
            avatarUrl = AVATAR_URL,
            htmlUrl = HTML_URL,
        )
}