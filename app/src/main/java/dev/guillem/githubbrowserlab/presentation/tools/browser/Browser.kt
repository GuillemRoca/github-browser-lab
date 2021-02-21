package dev.guillem.githubbrowserlab.presentation.tools.browser

import android.content.Context

interface Browser {
    fun launch(context: Context, url: String)
}