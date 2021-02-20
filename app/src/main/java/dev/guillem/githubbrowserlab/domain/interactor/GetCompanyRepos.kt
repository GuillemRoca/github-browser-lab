package dev.guillem.githubbrowserlab.domain.interactor

import dev.guillem.githubbrowserlab.domain.ReposRepository
import dev.guillem.githubbrowserlab.domain.entity.Repository
import dev.guillem.githubbrowserlab.domain.executor.PostExecutionThread
import dev.guillem.githubbrowserlab.domain.executor.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class GetCompanyRepos @Inject constructor(
    private val reposRepository: ReposRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
) : SingleUseCase<List<Repository>>(threadExecutor, postExecutionThread) {
    public override fun buildSingleUseCaseObservable(): Single<List<Repository>> =
        reposRepository.getCompanyRepos()
}