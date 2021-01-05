package com.res.jobjob.vm.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.model.data.Servicio
import com.res.jobjob.repository.RepoFactory
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.reposervicio.RepoServicio

class InformacionViewModel : ViewModel() {

    private val _servicio = MutableLiveData<Resource<Servicio>>()
    val servicio: LiveData<Resource<Servicio>>
        get() = _servicio

    private val authImp: RepoAuth = RepoFactory().getRepoAuth()
    private val servicioImp: RepoServicio = RepoFactory().getRepoServicio()

    fun getServicio(id: String) {
        servicioImp.getData(authImp.getId(), id, _servicio)
    }
}