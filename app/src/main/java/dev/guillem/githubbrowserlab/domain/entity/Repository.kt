package dev.guillem.githubbrowserlab.domain.entity

data class Repository(
    val id: Int,
    val name: String,
    val owner: Owner,
    val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
)
