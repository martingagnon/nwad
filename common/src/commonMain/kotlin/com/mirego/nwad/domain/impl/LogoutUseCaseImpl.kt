package com.mirego.nwad.domain.impl

import com.mirego.nwad.domain.LogoutUseCase
import com.mirego.nwad.models.graphql.AuthenticateToken
import com.mirego.nwad.repositories.TokenRepository
import com.mirego.trikot.streams.reactive.promise.Promise

class LogoutUseCaseImpl(private val tokenRepository: TokenRepository) : LogoutUseCase {
    override fun logout(): Promise<Unit> {
        tokenRepository.setToken(AuthenticateToken.NO_TOKEN, AuthenticateToken.NO_TOKEN)
        return Promise.resolve(Unit)
    }
}
