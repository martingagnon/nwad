package com.mirego.nwad.repositories.impl

import com.mirego.nwad.models.graphql.AuthenticateToken
import com.mirego.nwad.repositories.TokenRepository
import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.streams.reactive.Publishers
import com.russhwolf.settings.Settings
import org.reactivestreams.Publisher

class TokenRepositoryImpl() : TokenRepository {
    private val settings: Settings = Settings()
    private val accessTokenKey = "ACCESSTOKEN"
    private val refreshTokenKey = "REFRESHTOKEN"
    private val accessTokenPublisher = Publishers.behaviorSubject(AuthenticateToken.NO_TOKEN)
    private val refreshTokenPublisher = Publishers.behaviorSubject(AuthenticateToken.NO_TOKEN)

    override fun setToken(accessToken: AuthenticateToken, refreshToken: AuthenticateToken) {
        val accessTokenString: String =
            HttpConfiguration.json.encodeToString(AuthenticateToken.serializer(), accessToken)
        val refreshTokenString: String =
            HttpConfiguration.json.encodeToString(AuthenticateToken.serializer(), refreshToken)
        settings.putString(accessTokenKey, accessTokenString)
        settings.putString(refreshTokenKey, refreshTokenString)
        accessTokenPublisher.value = accessToken
        refreshTokenPublisher.value = refreshToken
    }

    override fun accessToken(): Publisher<AuthenticateToken> {
        return accessTokenPublisher
    }

    override fun refreshToken(): Publisher<AuthenticateToken> {
        return refreshTokenPublisher
    }

    init {
        settings.getString(accessTokenKey, "").takeIf { it.isNotEmpty() }?.let {
            accessTokenPublisher.value =
                HttpConfiguration.json.decodeFromString(AuthenticateToken.serializer(), it)
        }
        settings.getString(refreshTokenKey, "").takeIf { it.isNotEmpty() }?.let {
            refreshTokenPublisher.value =
                HttpConfiguration.json.decodeFromString(AuthenticateToken.serializer(), it)
        }
    }
}
