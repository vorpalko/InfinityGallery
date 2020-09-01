package com.example.scrollinggallery.data

class RepositoryProvider {

    fun getRemoteRepo() = RemoteRepository()

    fun getLocalRepo() = LocalRepository()

}