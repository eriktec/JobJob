package com.res.jobjob.vm.map

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.res.jobjob.ui.map.SupportFragment

class MapViewModel : ViewModel() {

    private var statusButton: Boolean = true
    lateinit var supportFragment: SupportFragment
    private val _nombreButton = MutableLiveData<String>().apply { value = "Conectar" }
    val nameButton: LiveData<String>
        get() = _nombreButton

    fun buttonClick(view: View) {
        if (statusButton) {
            _nombreButton.value = "Desconectar"
            statusButton = false
            supportFragment.start(view.context)
        } else {
            _nombreButton.value = "Conectar"
            statusButton = true
            supportFragment.desconectar()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("Hi", "Hola")
    }
}