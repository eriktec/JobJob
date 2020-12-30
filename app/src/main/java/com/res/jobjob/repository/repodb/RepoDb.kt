package com.res.jobjob.repository.repodb

import androidx.lifecycle.MutableLiveData
import com.res.jobjob.model.data.User

interface RepoDb {

    fun addUser(user: User, _success: MutableLiveData<*>)

    fun getUser(id: String, _success: MutableLiveData<*>)

    fun getJobs(_jobs: MutableLiveData<*>)
}