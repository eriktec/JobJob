package com.res.jobjob.ui.map

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.res.jobjob.R
import com.res.jobjob.common.base.BaseDialogs
import com.res.jobjob.databinding.FragmentMapBinding
import com.res.jobjob.repository.RepoFactory
import com.res.jobjob.vm.map.MapFactory
import com.res.jobjob.vm.map.MapViewModel

class MapFragment : Fragment() {

    private val viewModel: MapViewModel by lazy { ViewModelProvider(this, MapFactory(RepoFactory()))[MapViewModel::class.java] }
    private lateinit var fusedLocation: FusedLocationProviderClient
    private val onMapReadyImp: OnMapReadyImp = OnMapReadyImp()
    private lateinit var binding: FragmentMapBinding

    companion object {
        const val LOCATION_REQUEST_CODE = 42
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.handler = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.statusButton.setOnClickListener {
            when (viewModel.status) {
                true -> {
                    viewModel.switchStatus()
                    starLocation()
                }
                else -> {
                    viewModel.switchStatus()
                    viewModel.marker?.remove()
                    viewModel.removePosition()
                    fusedLocation.removeLocationUpdates(locationCallback)
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireActivity())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(onMapReadyImp)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode != LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                BaseDialogs.alertDialogAccept(requireContext(), "Alerta", "La aplicacion necesita acceso al permiso de ubicacion para funcionar") { _, _ -> }
            }
        }
    }

    private fun starLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocation.requestLocationUpdates(onMapReadyImp.locationRequest, locationCallback, Looper.myLooper())
            }
        } else fusedLocation.requestLocationUpdates(onMapReadyImp.locationRequest, locationCallback, Looper.myLooper())
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            p0?.locations!!.forEach {
                viewModel.latLng = LatLng(it.latitude, it.longitude)
                viewModel.marker?.remove()
                viewModel.marker = onMapReadyImp.googleMap.addMarker(MarkerOptions().position(viewModel.latLng!!).icon(BitmapDescriptorFactory.fromResource(R.drawable.caja)))
                onMapReadyImp.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                        CameraPosition.Builder().target(viewModel.latLng).zoom(15f).build()
                ))
                viewModel.addPosition()
            }
        }
    }
}