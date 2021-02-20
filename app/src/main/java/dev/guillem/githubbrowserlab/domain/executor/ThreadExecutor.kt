package dev.guillem.githubbrowserlab.domain.executor

import io.reactivex.Scheduler

interface ThreadExecutor {
    fun scheduler(): Scheduler
}