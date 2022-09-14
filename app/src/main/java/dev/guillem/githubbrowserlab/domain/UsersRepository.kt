package dev.guillem.githubbrowserlab.domain

import dev.guillem.githubbrowserlab.domain.entity.User
import io.reactivex.Single

interface UsersRepository {
    fun getUsersFromCSV(): Single<List<User>>
}