package dev.guillem.githubbrowserlab.presentation.model

import androidx.annotation.ColorRes

data class RepositoryView(
    val id: Int,
    val name: String,
    val owner: OwnerView,
    val htmlUrl: String,
    val description: String?,
    @ColorRes val backgroundColor: Int,
)
