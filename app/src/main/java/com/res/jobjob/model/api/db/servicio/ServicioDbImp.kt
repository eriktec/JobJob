package com.res.jobjob.model.api.db.servicio

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.model.data.Servicio

class ServicioDbImp : ServicioDb {

    private val dbReference = Firebase.database.getReference("Servicios_Socios")

    override fun getServicio(userId: String, id: String, resource: MutableLiveData<*>) {
        resource.value = Resource.Loading<Servicio>()
        val reference = dbReference.child(userId).child(id)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                resource.value = if (snapshot.exists()) {
                    val servicio: Servicio = snapshot.getValue(Servicio::class.java) as Servicio
                    Resource.Success(servicio)
                } else Resource.Error("Erro no existe este servicio")
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun getListServicio(id: String, resource: MutableLiveData<*>) {
        resource.value = Resource.Loading<List<Servicio>>()

    }
}