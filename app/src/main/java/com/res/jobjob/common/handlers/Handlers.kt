package com.res.jobjob.common.handlers

class Handlers {

    companion object {
        private const val PASSWORD_SIZE: Int = 6
    }

    fun checkPassword(text: String): String? {
        return when {
            text.isEmpty() -> null
            text.length >= PASSWORD_SIZE -> ""
            else -> "Contraseña muy corto"
        }
    }
}