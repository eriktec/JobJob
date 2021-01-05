package com.res.jobjob.repository.reposervicio

import androidx.lifecycle.MutableLiveData
import com.res.jobjob.model.api.db.servicio.ServicioDb

class RepoServicioImp(private val servicioDbImp: ServicioDb) : RepoServicio {

    override fun getData(userId: String, id: String, resource: MutableLiveData<*>) {
        servicioDbImp.getServicio(userId, id, resource)
    }
}