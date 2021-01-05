package com.res.jobjob.repository.repoauth

import androidx.lifecycle.MutableLiveData

interface RepoAuth {

    fun getId(): String

    fun statusLogin(email: String, password: String, _status: MutableLiveData<*>)

    fun statusRegister(email: String, password: String, _status: MutableLiveData<*>)

    fun signOut()

    fun exist(): Boolean
}