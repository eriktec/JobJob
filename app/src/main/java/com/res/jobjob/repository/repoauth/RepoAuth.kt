package com.res.jobjob.repository.repoauth

import androidx.lifecycle.MutableLiveData

interface RepoAuth {

    fun statusLogin(email: String, password: String, _status: MutableLiveData<*>)

    fun statusRegister(email: String, password: String, _status: MutableLiveData<*>)

    fun signOut()
}