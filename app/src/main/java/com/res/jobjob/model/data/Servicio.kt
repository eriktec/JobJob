package com.res.jobjob.model.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Servicio (
        val nombre: String = "",
        val trabajo: String = "",
        val detalle: String = "",
        val latitud: String = "",
        val longitud: String = "")