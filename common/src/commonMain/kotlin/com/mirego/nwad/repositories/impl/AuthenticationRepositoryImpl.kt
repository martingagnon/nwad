package com.mirego.nwad.repositories.impl

import com.mirego.nwad.repositories.AuthenticationRepository
import com.mirego.nwad.repositories.graphql.mutations.AuthenticateProviderUserMutation
import com.mirego.nwad.repositories.graphql.mutations.AuthenticateProviderUserResult
import com.mirego.trikot.graphql.GraphqlPublisherFactory
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.streams.reactive.promise.Promise

class AuthenticationRepositoryImpl(private val graphqlPublisherFactory: GraphqlPublisherFactory) :
    AuthenticationRepository {
    override fun authenticateWithToken(token: String): Promise<AuthenticateProviderUserResult> {
        val query = graphqlPublisherFactory
            .create(AuthenticateProviderUserMutation(token))
        query.execute()
        return Promise.from(query.map { it.data.authenticateProviderUser.result })
            .onSuccessReturn {
                it?.let { Promise.resolve(it) } ?: Promise.reject(IllegalStateException("No user returned"))
            }
    }
}
