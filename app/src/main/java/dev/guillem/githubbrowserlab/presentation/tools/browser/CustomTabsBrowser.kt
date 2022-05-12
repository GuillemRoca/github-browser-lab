package dev.guillem.githubbrowserlab.presentation.tools.browser

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import javax.inject.Inject

class CustomTabsBrowser @Inject constructor() : Browser {
    override fun launch(context: Context, url: String) {
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShareState(CustomTabsIntent.SHARE_STATE_ON)
            .setShowTitle(true)
            .build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}