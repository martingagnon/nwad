package com.mirego.nwad.domain

import com.mirego.nwad.models.graphql.AccountsUser
import com.mirego.trikot.streams.reactive.promise.Promise

interface LoginWithTokenUseCase {
    fun login(token: String): Promise<AccountsUser>
}
