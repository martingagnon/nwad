package com.mirego.nwad.domain

import org.reactivestreams.Publisher

interface IsLoggedUseCase {
    fun isLogged(): Publisher<Boolean>
}
