package dev.guillem.githubbrowserlab.presentation.executor

import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiThread @Inject constructor() : PostExecutionThread {
    override fun scheduler(): Scheduler = AndroidSchedulers.mainThread()
}