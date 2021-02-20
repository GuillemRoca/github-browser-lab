package dev.guillem.githubbrowserlab.data.mapper

interface Mapper<E, D> {
    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E
}