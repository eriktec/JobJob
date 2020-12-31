package com.res.jobjob.model.api.db

import com.firebase.geofire.GeoFire
import com.firebase.geofire.GeoLocation
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GeoFireProviderImp : GeoFireProvider {

    private val firebaseDB: FirebaseDatabase = Firebase.database
    private val geoFire: GeoFire

    init {
        geoFire = GeoFire(firebaseDB.getReference("Socios_Activos"))
    }

    override fun saveLocation(id: String, latLng: LatLng) {
        geoFire.setLocation(id, GeoLocation(latLng.latitude, latLng.longitude))
    }

    override fun removeLocation(id: String) {
        geoFire.removeLocation(id)
    }
}