package dev.guillem.githubbrowserlab.presentation.tools.browser

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.presentation.tools.extensions.getColorFromAttr
import javax.inject.Inject

class CustomTabsBrowser @Inject constructor() : Browser {
    override fun launch(context: Context, url: String) {
        val params = CustomTabColorSchemeParams.Builder()
            .setNavigationBarColor(context.getColorFromAttr(R.attr.colorOnPrimary))
            .setToolbarColor(context.getColorFromAttr(R.attr.colorPrimary))
            .setSecondaryToolbarColor(context.getColorFromAttr(R.attr.colorPrimaryVariant))
            .build()
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShareState(CustomTabsIntent.SHARE_STATE_ON)
            .setDefaultColorSchemeParams(params)
            .setShowTitle(true)
            .build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}