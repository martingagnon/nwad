package com.mirego.nwad.repositories

import com.mirego.nwad.models.Moment
import org.reactivestreams.Publisher

interface MomentsRepository {
    fun getMoments(): Publisher<List<Moment>>
}
