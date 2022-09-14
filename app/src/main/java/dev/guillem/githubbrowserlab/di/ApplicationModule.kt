package dev.guillem.githubbrowserlab.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.guillem.githubbrowserlab.data.ApiService
import dev.guillem.githubbrowserlab.data.ReposDataRepository
import dev.guillem.githubbrowserlab.data.UsersDataSource
import dev.guillem.githubbrowserlab.data.executor.JobExecutor
import dev.guillem.githubbrowserlab.data.mapper.UserMapper
import dev.guillem.githubbrowserlab.domain.ReposRepository
import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import dev.guillem.githubbrowserlab.presentation.executor.UiThread
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideReposRepository(reposDataRepository: ReposDataRepository): ReposRepository =
        reposDataRepository

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