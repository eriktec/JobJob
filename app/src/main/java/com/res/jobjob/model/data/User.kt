package com.res.jobjob.model.data

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class User(
        val id: String = "",
        val nombre: String = "",
        val correo: String = "",
        val oficio: String = "",
        val telefono: String = "") : Serializable {
}
