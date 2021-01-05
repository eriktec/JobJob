package com.res.jobjob.repository.repoauth

import androidx.lifecycle.MutableLiveData
import com.res.jobjob.model.api.auth.Auth

class RepoAuthImp(private val auth: Auth) : RepoAuth {

    override fun getId(): String {
        return auth.getId()
    }

    override fun statusLogin(email: String, password: String, _status: MutableLiveData<*>) {
        auth.login(email, password, _status)
    }

    override fun statusRegister(email: String, password: String, _status: MutableLiveData<*>) {
        auth.signIn(email, password, _status)
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun exist(): Boolean {
        return auth.exist()
    }
}