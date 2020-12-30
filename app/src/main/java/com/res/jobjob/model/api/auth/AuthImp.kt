package com.res.jobjob.model.api.auth

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.res.jobjob.common.vo.Resource

class AuthImp : Auth {

    private val auth: FirebaseAuth = Firebase.auth

    override fun signIn(email: String, password: String, _status: MutableLiveData<*>) {
        _status.value = Resource.Loading<String>()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            _status.value = if (it.isSuccessful) Resource.Success(auth.uid.toString())
            else Resource.Error("No se pudo registrar el usuario")
        }
    }

    override fun login(email: String, password: String, _status: MutableLiveData<*>) {
        _status.value = Resource.Loading<String>()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            _status.value = when (it.isSuccessful) {
                true -> Resource.Success(auth.uid.toString())
                else -> Resource.Error("El usuario no existe")
            }
        }
    }

    override fun signOut() {
        auth.signOut()
    }
}