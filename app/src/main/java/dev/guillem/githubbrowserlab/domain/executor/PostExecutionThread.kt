package dev.guillem.githubbrowserlab.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun scheduler(): Scheduler
}