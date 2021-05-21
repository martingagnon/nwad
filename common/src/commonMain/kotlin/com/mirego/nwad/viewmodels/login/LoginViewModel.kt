package com.mirego.nwad.viewmodels.login

import com.mirego.trikot.streams.reactive.promise.Promise

interface LoginViewModel {
    fun login(token: String): Promise<Unit>
}
