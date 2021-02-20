package dev.guillem.githubbrowserlab.data

import dev.guillem.githubbrowserlab.data.mapper.RepositoryMapper
import dev.guillem.githubbrowserlab.domain.ReposRepository
import dev.guillem.githubbrowserlab.domain.entity.Repository
import io.reactivex.Single
import javax.inject.Inject

class ReposDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val repositoryMapper: RepositoryMapper,
) : ReposRepository {
    override fun getCompanyRepos(): Single<List<Repository>> = apiService.getCompanyRepos()
        .map { list ->
            list.map { listItem ->
                repositoryMapper.mapFromEntity(listItem)
            }
        }
}