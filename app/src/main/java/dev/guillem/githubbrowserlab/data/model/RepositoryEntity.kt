package dev.guillem.githubbrowserlab.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: OwnerEntity,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("fork") val fork: Boolean,
)
