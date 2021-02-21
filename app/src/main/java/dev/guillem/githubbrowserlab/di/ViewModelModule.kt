package dev.guillem.githubbrowserlab.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.guillem.githubbrowserlab.presentation.tools.browser.Browser
import dev.guillem.githubbrowserlab.presentation.tools.browser.CustomTabsBrowser

@InstallIn(ViewModelComponent::class)
@Module
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideBrowser(customTabsBrowser: CustomTabsBrowser): Browser = customTabsBrowser
}