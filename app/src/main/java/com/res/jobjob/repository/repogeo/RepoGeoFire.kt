package com.res.jobjob.repository.repogeo

import com.google.android.gms.maps.model.LatLng

interface RepoGeoFire {

    fun updatePosition(id: String, latLng: LatLng)

    fun removePosition(id: String)
}