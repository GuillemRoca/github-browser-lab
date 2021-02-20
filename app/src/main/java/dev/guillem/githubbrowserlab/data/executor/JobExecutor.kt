package dev.guillem.githubbrowserlab.data.executor

import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobExecutor @Inject constructor() : ThreadExecutor {
    override fun scheduler(): Scheduler = Schedulers.io()
}