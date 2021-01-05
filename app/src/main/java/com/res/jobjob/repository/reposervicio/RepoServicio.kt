package com.res.jobjob.repository.reposervicio

import androidx.lifecycle.MutableLiveData

interface RepoServicio {

    fun getData(userId: String, id: String, resource: MutableLiveData<*>)
}