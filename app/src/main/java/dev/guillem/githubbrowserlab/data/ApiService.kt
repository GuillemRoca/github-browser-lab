package dev.guillem.githubbrowserlab.data

import dev.guillem.githubbrowserlab.data.model.RepositoryEntity
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/orgs/github/repos")
    fun getCompanyRepos(): Single<List<RepositoryEntity>>
}