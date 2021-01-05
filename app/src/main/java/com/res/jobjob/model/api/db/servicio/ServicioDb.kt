package com.res.jobjob.model.api.db.servicio

import androidx.lifecycle.MutableLiveData

interface ServicioDb {

    fun getServicio(userId: String, id: String, resource: MutableLiveData<*>)

    fun getListServicio(id: String, resource: MutableLiveData<*>)
}