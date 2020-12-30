package com.res.jobjob.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.res.jobjob.R

class SupportFragment(supportMapFragment: SupportMapFragment, activity: FragmentActivity) : OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var locationRequest: LocationRequest
    private var fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    private lateinit var marker: Marker

    init {
        supportMapFragment.getMapAsync(this)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            p0?.locations?.forEach { location ->
                if (::marker.isInitialized) marker.remove()
                val latLng = LatLng(location.latitude, location.longitude)
                marker = googleMap.addMarker(MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.caja)))
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                        CameraPosition.Builder().target(latLng).zoom(16f).build())
                )
            }
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        locationRequest = LocationRequest().apply {
            interval = 1000
            fastestInterval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 1.5f
        }
    }

    fun start(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                PackageManager.PERMISSION_GRANTED -> fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            }
        } else fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

    }

    fun desconectar() {
        if (::marker.isInitialized) marker.remove()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}