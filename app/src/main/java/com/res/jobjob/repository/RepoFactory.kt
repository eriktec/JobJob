package com.res.jobjob.repository

import com.res.jobjob.model.api.auth.AuthImp
import com.res.jobjob.model.api.db.user.DbServiceImp
import com.res.jobjob.model.api.db.geofire.GeoFireProviderImp
import com.res.jobjob.model.api.db.servicio.ServicioDbImp
import com.res.jobjob.model.api.db.token.TokenProviderImp
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.repoauth.RepoAuthImp
import com.res.jobjob.repository.repodb.RepoDb
import com.res.jobjob.repository.repodb.RepoDbImp
import com.res.jobjob.repository.repogeo.RepoGeoFire
import com.res.jobjob.repository.repogeo.RepoGeoFireImp
import com.res.jobjob.repository.reposervicio.RepoServicio
import com.res.jobjob.repository.reposervicio.RepoServicioImp
import com.res.jobjob.repository.repotoken.RepoToken
import com.res.jobjob.repository.repotoken.RepoTokenImp

class RepoFactory {

    fun getRepoAuth(): RepoAuth {
        return RepoAuthImp(AuthImp())
    }

    fun getRepoDb(): RepoDb {
        return RepoDbImp(DbServiceImp())
    }

    fun getRepoGeoFire(): RepoGeoFire {
        return RepoGeoFireImp(GeoFireProviderImp())
    }

    fun getRepoToken(): RepoToken {
        return RepoTokenImp(TokenProviderImp())
    }

    fun getRepoServicio(): RepoServicio {
        return RepoServicioImp(ServicioDbImp())
    }
}