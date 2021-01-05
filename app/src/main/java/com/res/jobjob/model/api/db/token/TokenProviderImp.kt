package com.res.jobjob.model.api.db.token

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.res.jobjob.model.data.Token

class TokenProviderImp : TokenProvider {

    private val firebaseDb: FirebaseDatabase = Firebase.database

    override fun create(id: String) {
        val reference = firebaseDb.getReference("Tokens")
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            val token: Token = Token(it.token)
            reference.child(id).setValue(token)
        }
    }
}