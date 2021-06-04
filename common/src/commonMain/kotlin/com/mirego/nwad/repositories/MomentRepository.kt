package com.mirego.nwad.repositories

import com.mirego.trikot.streams.reactive.promise.Promise

interface MomentRepository {
    fun createMoment(title: String, pictureUrl: String, parentMoment: String?): Promise<Unit>
}