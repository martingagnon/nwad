package com.mirego.nwad.domain.impl

import com.mirego.nwad.domain.IsLoggedUseCase
import com.mirego.nwad.models.graphql.AuthenticateToken
import com.mirego.nwad.repositories.TokenRepository
import com.mirego.trikot.streams.reactive.map
import org.reactivestreams.Publisher

class IsLoggedUseCaseImpl(private val tokenRepository: TokenRepository) : IsLoggedUseCase {
    override fun isLogged(): Publisher<Boolean> {
        return tokenRepository.accessToken().map { it != AuthenticateToken.NO_TOKEN }
    }
}
