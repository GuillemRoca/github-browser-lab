package dev.guillem.githubbrowserlab.data.entity

import com.google.gson.annotations.SerializedName

data class OwnerRemote (
    @SerializedName("login") val login : String,
    @SerializedName("id") val id : Int,
    @SerializedName("avatar_url") val avatarUrl : String,
    @SerializedName("html_url") val htmlUrl : String,
)