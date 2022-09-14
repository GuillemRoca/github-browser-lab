package dev.guillem.githubbrowserlab.data

import android.content.Context
import android.util.Log
import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.data.mapper.RepositoryMapper
import dev.guillem.githubbrowserlab.data.mapper.UserMapper
import dev.guillem.githubbrowserlab.domain.ReposRepository
import dev.guillem.githubbrowserlab.domain.entity.Repository
import dev.guillem.githubbrowserlab.domain.entity.User
import io.reactivex.Single
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

class ReposDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val repositoryMapper: RepositoryMapper,
    private val usersDataSource: UsersDataSource,
    private val userMapper: UserMapper,
) : ReposRepository {
    override fun getCompanyRepos(): Single<List<Repository>> = apiService.getCompanyRepos()
        .map { list ->
            list.map { listItem ->
                repositoryMapper.mapFromEntity(listItem)
            }
        }

    override fun getUsersFromCSV(): Single<List<User>> =
        Single.just(usersDataSource.getUsersFromCSV().map { user ->
            userMapper.mapFromEntity(user)
        })
}