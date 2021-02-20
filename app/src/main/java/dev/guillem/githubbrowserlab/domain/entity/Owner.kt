package dev.guillem.githubbrowserlab.domain.entity

data class Owner(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val htmlUrl: String,
)