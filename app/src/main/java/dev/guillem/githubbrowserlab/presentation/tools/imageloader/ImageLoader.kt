package dev.guillem.githubbrowserlab.presentation.tools.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun load(imageUrl: String, imageView: ImageView)
}