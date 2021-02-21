package dev.guillem.githubbrowserlab.presentation.tools.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(val context: Context) : ImageLoader {
    override fun load(imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }
}