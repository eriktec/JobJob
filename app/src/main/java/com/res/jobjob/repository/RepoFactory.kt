package com.res.jobjob.repository

import com.res.jobjob.model.api.auth.AuthImp
import com.res.jobjob.model.api.db.DbServiceImp
import com.res.jobjob.repository.repoauth.RepoAuth
import com.res.jobjob.repository.repoauth.RepoAuthImp
import com.res.jobjob.repository.repodb.RepoDb
import com.res.jobjob.repository.repodb.RepoDbImp

class RepoFactory {

    fun getRepoAuth(): RepoAuth {
        return RepoAuthImp(AuthImp())
    }

    fun getRepoDb(): RepoDb {
        return RepoDbImp(DbServiceImp())
    }
}