package com.res.jobjob.vm.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.repogeo.RepoGeoFire

class MapViewModel(private val repoAuth: RepoAuth,private val repoGeoFire: RepoGeoFire) : ViewModel() {

    var status: Boolean = true
    var marker: Marker? = null
    var latLng: LatLng? = null

    private val _nameButton = MutableLiveData<String>().apply { value = "Conectar" }
    val nameButton: LiveData<String>
        get() = _nameButton

    fun switchStatus() {
        if (this.status) {
            _nameButton.value = "Desconectar"
            status = false
            return
        }
        _nameButton.value = "Conectar"
        status = true
    }

    fun addPosition() {
        repoGeoFire.updatePosition(repoAuth.getId(), latLng!!)
    }

    fun removePosition() {
        repoGeoFire.removePosition(repoAuth.getId())
    }
}