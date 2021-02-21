package dev.guillem.githubbrowserlab.presentation.tools.browser

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.google.android.material.color.MaterialColors
import dev.guillem.githubbrowserlab.R
import javax.inject.Inject

class CustomTabsBrowser @Inject constructor() : Browser {
    override fun launch(context: Context, url: String) {
        val params = CustomTabColorSchemeParams.Builder()
            .setNavigationBarColor(
                MaterialColors.getColor(
                    context, R.attr.colorOnPrimary, ContextCompat.getColor(context, R.color.white)
                )
            )
            .setToolbarColor(
                MaterialColors.getColor(
                    context,
                    R.attr.colorPrimary,
                    ContextCompat.getColor(context, R.color.teal_200)
                )
            )
            .setSecondaryToolbarColor(
                MaterialColors.getColor(
                    context,
                    R.attr.colorPrimaryVariant,
                    ContextCompat.getColor(context, R.color.teal_700)
                )
            )
            .build()
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShareState(CustomTabsIntent.SHARE_STATE_ON)
            .setDefaultColorSchemeParams(params)
            .setShowTitle(true)
            .build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}