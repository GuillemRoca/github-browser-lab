package dev.guillem.githubbrowserlab.presentation.model

import androidx.annotation.AttrRes

data class RepositoryView(
    val id: Int,
    val name: String,
    val owner: OwnerView,
    val htmlUrl: String,
    val description: String?,
    @AttrRes val backgroundAttrColor: Int,
)
