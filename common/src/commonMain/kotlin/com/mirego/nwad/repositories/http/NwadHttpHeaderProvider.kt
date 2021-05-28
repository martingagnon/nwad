package com.mirego.nwad.repositories.http

import com.mirego.nwad.models.graphql.AuthenticateToken
import com.mirego.nwad.repositories.TokenRepository
import com.mirego.trikot.http.HttpHeaderProvider
import com.mirego.trikot.http.RequestBuilder
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.map
import org.reactivestreams.Publisher

class NwadHttpHeaderProvider(private val tokenRepository: TokenRepository) : HttpHeaderProvider {
    override fun headerForURLRequest(
        cancellableManager: CancellableManager,
        requestBuilder: RequestBuilder
    ): Publisher<Map<String, String>> {
        return tokenRepository
            .accessToken()
            .map {
                if (it != AuthenticateToken.NO_TOKEN) {
                    mapOf("Authorization" to it.token)
                } else {
                    emptyMap()
                }
            }
    }

    override fun processHttpError(requestBuilder: RequestBuilder, error: Throwable) {
    }
}
