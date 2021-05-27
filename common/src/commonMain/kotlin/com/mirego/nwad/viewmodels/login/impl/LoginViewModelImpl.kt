package com.mirego.nwad.viewmodels.login.impl

import com.mirego.nwad.domain.LoginWithTokenUseCase
import com.mirego.nwad.viewmodels.login.LoginViewModel
import com.mirego.trikot.streams.reactive.promise.Promise

class LoginViewModelImpl(val loginWithTokenUseCase: LoginWithTokenUseCase) : LoginViewModel {
    override fun login(token: String): Promise<Unit> {
        return loginWithTokenUseCase
            .login(token)
            .onSuccessReturn { Promise.resolve(Unit) }
    }
}
