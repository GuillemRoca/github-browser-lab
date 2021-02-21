package dev.guillem.githubbrowserlab.presentation.main

import dev.guillem.githubbrowserlab.presentation.model.RepositoryView

interface RepositoryClickListener {
    fun onRepositoryLongClick(repositoryView: RepositoryView)
}