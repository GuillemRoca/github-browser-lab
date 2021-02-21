package dev.guillem.githubbrowserlab.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import dev.guillem.githubbrowserlab.presentation.tools.imageloader.GlideImageLoader
import dev.guillem.githubbrowserlab.presentation.tools.imageloader.ImageLoader

@InstallIn(ActivityComponent::class)
@Module
object ActivityModule {
    @Provides
    @ActivityScoped
    fun provideImageLoader(@ActivityContext context: Context): ImageLoader = GlideImageLoader(context)
}