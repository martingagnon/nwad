package com.mirego.nwad.repositories

import com.mirego.nwad.repositories.graphql.models.AuthenticateProviderUserResult
import com.mirego.trikot.streams.reactive.promise.Promise

interface AuthenticationRepository {
    fun authenticateWithToken(token: String): Promise<AuthenticateProviderUserResult>
}
