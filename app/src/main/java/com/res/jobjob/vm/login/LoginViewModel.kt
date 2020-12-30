package com.res.jobjob.vm.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.model.data.User
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.repodb.RepoDb

class LoginViewModel(private val repo: RepoAuth, private val repoDb: RepoDb) : ViewModel() {

    private val _status = MutableLiveData<Resource<String>>()
    val status: LiveData<Resource<String>>
        get() = _status

    fun loginUser(email: String, password: String) {
        repo.statusLogin(email, password, _status)
    }

    fun getUser(id: String): LiveData<Resource<User>> {
        val status = MutableLiveData<Resource<User>>()
        repoDb.getUser(id, status)
        return status
    }
}