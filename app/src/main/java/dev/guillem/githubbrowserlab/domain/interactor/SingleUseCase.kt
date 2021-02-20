package dev.guillem.githubbrowserlab.domain.interactor

import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T> protected constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables: CompositeDisposable = CompositeDisposable()
    protected abstract fun buildSingleUseCaseObservable(): Single<T>
    open fun execute(observer: DisposableSingleObserver<T>) {
        val observable = buildSingleUseCaseObservable()
            .subscribeOn(threadExecutor.scheduler())
            .observeOn(postExecutionThread.scheduler())
        disposables.add(observable.subscribeWith(observer))
    }

    open fun clear() {
        disposables.clear()
    }
}