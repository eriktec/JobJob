package com.res.jobjob.repository.repogeo

import com.google.android.gms.maps.model.LatLng
import com.res.jobjob.model.api.db.geofire.GeoFireProvider

class RepoGeoFireImp(private val geoFireProvider: GeoFireProvider) : RepoGeoFire {

    override fun updatePosition(id: String, latLng: LatLng) {
        geoFireProvider.saveLocation(id, latLng)
    }

    override fun removePosition(id: String) {
        geoFireProvider.removeLocation(id)
    }
}