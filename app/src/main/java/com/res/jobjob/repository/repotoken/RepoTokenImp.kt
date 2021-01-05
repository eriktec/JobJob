package com.res.jobjob.repository.repotoken

import com.res.jobjob.model.api.db.token.TokenProvider

class RepoTokenImp(private val tokenProvider: TokenProvider) : RepoToken {

    override fun crearToken(id: String) {
        tokenProvider.create(id)
    }
}