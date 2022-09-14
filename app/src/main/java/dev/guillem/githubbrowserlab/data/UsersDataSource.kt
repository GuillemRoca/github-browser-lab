package dev.guillem.githubbrowserlab.data

import android.content.Context
import android.util.Log
import dev.guillem.githubbrowserlab.R
import dev.guillem.githubbrowserlab.data.mapper.UserMapper
import dev.guillem.githubbrowserlab.data.model.UserEntity
import dev.guillem.githubbrowserlab.domain.entity.User
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

class UsersDataSource @Inject constructor(
    private val context: Context,
    private val userMapper: UserMapper
) {
    fun getUsersFromCSV(): List<UserEntity> {
        val reader =
            BufferedReader(InputStreamReader(context.resources.openRawResource(R.raw.data)))
        val csvFile = TSVReader(reader)
        val resultList = mutableListOf<UserEntity>()
        try {
            var line = arrayOf<String>()
            while (csvFile.readRow().also {
                    if (it != null) {
                        line = it
                    }
                } != null) {
                resultList.add(userMapper.mapFromArray(line))
            }
        } catch (ex: IOException) {
            Log.e(UsersDataSource::class.java.name, "Error in reading CSV file:" + ex.message)
        }
        return resultList
    }
}