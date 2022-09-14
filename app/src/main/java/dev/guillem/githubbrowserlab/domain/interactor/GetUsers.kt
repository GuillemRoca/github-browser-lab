package dev.guillem.githubbrowserlab.domain.interactor

import dev.guillem.githubbrowserlab.domain.ReposRepository
import dev.guillem.githubbrowserlab.domain.entity.User
import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val reposRepository: ReposRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
) : SingleUseCase<List<User>>(threadExecutor, postExecutionThread) {
    public override fun buildSingleUseCaseObservable(): Single<List<User>> =
        reposRepository.getUsersFromCSV()
}