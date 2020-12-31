package com.res.jobjob.model.api.db

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.model.data.User

class DbServiceImp : DbService {

    private val firebaseDb: FirebaseDatabase = Firebase.database

    override fun addUser(user: User, _success: MutableLiveData<*>) {
        _success.value = Resource.Loading<String>()
        val reference = firebaseDb.getReference("Socios").child(user.id)
        reference.setValue(user).addOnCompleteListener {
            _success.value = if (it.isSuccessful) Resource.Success("Se agrego el usuario en el DB")
            else Resource.Error("No se pudo añadir el usuario en la DB")
        }
    }

    override fun getUser(id: String, _success: MutableLiveData<*>) {
        _success.value = Resource.Loading<User>()
        val reference = firebaseDb.getReference("Socios").child(id)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _success.value = if (snapshot.exists()) {
                    val user: User = snapshot.getValue(User::class.java)!!
                    Resource.Success(user)
                } else Resource.Error("No existe el usuario")
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    override fun getJobs(_jobs: MutableLiveData<*>) {
        _jobs.value = arrayListOf("Elija su Oficio")
        val reference = firebaseDb.getReference("Empleos")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jobs: ArrayList<String> = arrayListOf()
                snapshot.children.forEach {
                    val name: String = it.child("nombre").value.toString()
                    jobs.add(name)
                }
                _jobs.value = jobs
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    override fun editUser(user: User, _success: MutableLiveData<*>) {
        _success.value = Resource.Loading<String>()
        val reference = firebaseDb.getReference("Socios").child(user.id)
        reference.setValue(user).addOnCompleteListener {
            _success.value = if (it.isSuccessful) Resource.Success("Success")
            else Resource.Error("Error")
        }
    }
}