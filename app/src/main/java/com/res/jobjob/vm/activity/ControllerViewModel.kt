package com.res.jobjob.vm.activity

import androidx.lifecycle.ViewModel
import com.res.jobjob.repository.RepoFactory
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.repotoken.RepoToken
import com.res.jobjob.repository.repotoken.RepoTokenImp

class ControllerViewModel : ViewModel() {

    private val repoAutImp: RepoAuth = RepoFactory().getRepoAuth()
    private val repoTokenImp: RepoToken = RepoFactory().getRepoToken()

    fun salir() {
        repoAutImp.signOut()
    }

    fun crearToken() {
        repoTokenImp.crearToken(repoAutImp.getId())
    }
}