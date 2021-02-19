package dev.guillem.githubbrowserlab.data.entity

import com.google.gson.annotations.SerializedName

data class RepositoryRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: OwnerRemote,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("fork") val fork: Boolean,
)
