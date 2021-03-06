package com.res.jobjob.model.api.db.user

import androidx.lifecycle.MutableLiveData
import com.res.jobjob.model.data.User

interface DbService {

    fun addUser(user: User, _success: MutableLiveData<*>)

    fun getUser(id: String, _success: MutableLiveData<*>)

    fun getJobs(_jobs: MutableLiveData<*>)

    fun editUser(user: User, _success: MutableLiveData<*>)
}