package dev.guillem.githubbrowserlab.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.guillem.githubbrowserlab.data.UsersDataRepository
import dev.guillem.githubbrowserlab.data.UsersDataSource
import dev.guillem.githubbrowserlab.data.executor.JobExecutor
import dev.guillem.githubbrowserlab.data.mapper.UserMapper
import dev.guillem.githubbrowserlab.domain.UsersRepository
import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import dev.guillem.githubbrowserlab.presentation.executor.UiThread
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @Provides
    @Singleton
    fun provideUsersRepository(usersDataRepository: UsersDataRepository): UsersRepository =
        usersDataRepository

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideUserDataSource(
        @ApplicationContext appContext: Context,
        userMapper: UserMapper
    ): UsersDataSource =
        UsersDataSource(appContext, userMapper)
}