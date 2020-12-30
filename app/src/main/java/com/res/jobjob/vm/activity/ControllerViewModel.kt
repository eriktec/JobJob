package com.res.jobjob.vm.activity

import androidx.lifecycle.ViewModel
import com.res.jobjob.repository.RepoFactory
import com.res.jobjob.repository.repoauth.RepoAuth

class ControllerViewModel : ViewModel() {

    private val repoAutImp: RepoAuth = RepoFactory().getRepoAuth()

    fun salir() {
        repoAutImp.signOut()
    }
}