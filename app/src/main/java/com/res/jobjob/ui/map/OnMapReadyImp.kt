package com.res.jobjob.ui.map

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class OnMapReadyImp : OnMapReadyCallback {

    lateinit var googleMap: GoogleMap
    val locationRequest: LocationRequest = LocationRequest()

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        locationRequest.apply {
            interval = 1000
            fastestInterval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 5f
        }
    }
}