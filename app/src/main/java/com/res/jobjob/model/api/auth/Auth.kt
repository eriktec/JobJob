package com.res.jobjob.model.api.auth

import androidx.lifecycle.MutableLiveData

interface Auth {

    fun signIn(email: String, password: String, _status: MutableLiveData<*>)

    fun login(email: String, password: String, _status: MutableLiveData<*>)

    fun signOut()
}