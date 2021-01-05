package com.res.jobjob.vm.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.res.jobjob.repository.RepoFactory
import java.lang.IllegalArgumentException

class MapFactory(private val repoFactory: RepoFactory) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MapViewModel::class.java)) MapViewModel(repoFactory.getRepoAuth(), repoFactory.getRepoGeoFire()) as T
        else throw IllegalArgumentException("View model no fount")
    }
}