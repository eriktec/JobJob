package com.res.jobjob.vm.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.res.jobjob.repository.RepoFactory
import java.lang.IllegalArgumentException

class LoginFactory(private val repoFactory: RepoFactory) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            LoginViewModel(repoFactory.getRepoAuth(), repoFactory.getRepoDb()) as T
        else throw IllegalArgumentException("View Model no found")
    }
}