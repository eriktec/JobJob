package com.res.jobjob.vm.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.model.data.User
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.repodb.RepoDb

class RegisterViewModel(private val repoAuth: RepoAuth, private val repoDb: RepoDb) : ViewModel() {

    lateinit var user: User

    private val _jobs = MutableLiveData<List<String>>()
    val jobs: LiveData<List<String>>
        get() = _jobs

    fun fetchJobs() {
        repoDb.getJobs(_jobs)
    }

    fun registrarUsuario(email: String, password: String): LiveData<Resource<String>> {
        val successAuth = MutableLiveData<Resource<String>>()
        repoAuth.statusRegister(email, password, successAuth)
        return successAuth
    }

    fun addUserDb(): LiveData<Resource<String>> {
        val successDb = MutableLiveData<Resource<String>>()
        repoDb.addUser(user, successDb)
        return successDb
    }
}