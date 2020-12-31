package com.res.jobjob.model.api.db

import com.google.android.gms.maps.model.LatLng

interface GeoFireProvider {

    fun saveLocation(id: String, latLng: LatLng)

    fun removeLocation(id: String)
}