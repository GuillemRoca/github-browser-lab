package dev.guillem.githubbrowserlab.presentation.tools.extensions

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors

@ColorInt
fun Context.getColorFromAttr(@AttrRes attrColor: Int): Int {
    return MaterialColors.getColor(this, attrColor, Color.BLACK)
}