package com.mirego.nwad.domain

import com.mirego.nwad.models.Moment
import org.reactivestreams.Publisher

interface FetchMomentsUseCase {
    fun fetchMoments(): Publisher<List<Moment>>
}
