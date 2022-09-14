package dev.guillem.githubbrowserlab.data

import dev.guillem.githubbrowserlab.data.mapper.UserMapper
import dev.guillem.githubbrowserlab.domain.UsersRepository
import dev.guillem.githubbrowserlab.domain.entity.User
import io.reactivex.Single
import javax.inject.Inject

class UsersDataRepository @Inject constructor(
    private val usersDataSource: UsersDataSource,
    private val userMapper: UserMapper,
) : UsersRepository {
    override fun getUsersFromCSV(): Single<List<User>> =
        Single.just(usersDataSource.getUsersFromCSV().map { user ->
            userMapper.mapFromEntity(user)
        })
}