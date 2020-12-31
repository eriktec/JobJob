package com.res.jobjob.repository.repodb

import androidx.lifecycle.MutableLiveData
import com.res.jobjob.model.api.db.DbService
import com.res.jobjob.model.data.User

class RepoDbImp(private val dbService: DbService) : RepoDb {

    override fun addUser(user: User, _success: MutableLiveData<*>) {
        dbService.addUser(user, _success)
    }

    override fun getUser(id: String, _success: MutableLiveData<*>) {
        dbService.getUser(id, _success)
    }

    override fun getJobs(_jobs: MutableLiveData<*>) {
        dbService.getJobs(_jobs)
    }

    override fun editUser(user: User, _success: MutableLiveData<*>) {
        dbService.editUser(user, _success)
    }
}