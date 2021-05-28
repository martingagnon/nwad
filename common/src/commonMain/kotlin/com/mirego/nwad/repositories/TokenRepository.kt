package com.mirego.nwad.repositories

import com.mirego.nwad.models.graphql.AuthenticateToken
import org.reactivestreams.Publisher

interface TokenRepository {
    fun setToken(accessToken: AuthenticateToken, refreshToken: AuthenticateToken)
    fun accessToken(): Publisher<AuthenticateToken>
    fun refreshToken(): Publisher<AuthenticateToken>
}
