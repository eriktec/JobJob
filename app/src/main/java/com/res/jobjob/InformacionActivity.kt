package com.res.jobjob

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.databinding.ActivityInformacionBinding
import com.res.jobjob.vm.activity.InformacionViewModel

class InformacionActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: InformacionViewModel by lazy { ViewModelProvider(this)[InformacionViewModel::class.java] }
    private lateinit var binding: ActivityInformacionBinding
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_informacion)
        viewModel.getServicio("1")
        val mapa = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapa.getMapAsync(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        viewModel.servicio.observe(this, {
            when (it) {
                is Resource.Loading -> { }
                is Resource.Success -> {
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                    .target(LatLng(it.data.latitud.toDouble(), it.data.longitud.toDouble()))
                                    .zoom(15f).build()
                    ))
                    binding.apply {
                        nombreText.text = "${nombreText.text} ${it.data.nombre}"
                        trabajoText.text = "${trabajoText.text} ${it.data.trabajo}"
                        detallesText.text = it.data.detalle
                    }
                }
                is Resource.Error -> { }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backActivity()
    }

    private fun backActivity() {
        val intent = Intent(this, ControlActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }
}