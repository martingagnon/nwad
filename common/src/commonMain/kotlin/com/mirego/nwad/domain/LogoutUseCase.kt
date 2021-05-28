package com.mirego.nwad.domain

import com.mirego.trikot.streams.reactive.promise.Promise

interface LogoutUseCase {
    fun logout(): Promise<Unit>
}
