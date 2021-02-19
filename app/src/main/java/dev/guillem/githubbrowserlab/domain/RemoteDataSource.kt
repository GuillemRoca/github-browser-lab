package dev.guillem.githubbrowserlab.domain

import dev.guillem.githubbrowserlab.domain.entity.Repository
import io.reactivex.Single

interface RemoteDataSource {
    fun getCompanyRepos(): Single<List<Repository>>
}