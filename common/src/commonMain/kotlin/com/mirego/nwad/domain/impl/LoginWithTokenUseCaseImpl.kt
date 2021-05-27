package com.mirego.nwad.domain.impl

import com.mirego.nwad.domain.LoginWithTokenUseCase
import com.mirego.nwad.models.graphql.AccountsUser
import com.mirego.nwad.repositories.AuthenticationRepository
import com.mirego.trikot.streams.reactive.promise.Promise

class LoginWithTokenUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    LoginWithTokenUseCase {
    override fun login(token: String): Promise<AccountsUser> {
        return authenticationRepository.authenticateWithToken(token)
            .onSuccessReturn {
                Promise.resolve(it.user)
            }
    }
}
