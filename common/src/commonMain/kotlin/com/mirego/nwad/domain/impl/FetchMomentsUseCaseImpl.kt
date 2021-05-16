package com.mirego.nwad.domain.impl

import com.mirego.nwad.domain.FetchMomentsUseCase
import com.mirego.nwad.models.Moment
import com.mirego.nwad.repositories.MomentsRepository
import com.mirego.trikot.streams.reactive.shared
import org.reactivestreams.Publisher

class FetchMomentsUseCaseImpl(private val momentsRepo: MomentsRepository) :
    FetchMomentsUseCase {
    override fun fetchMoments(): Publisher<List<Moment>> {
        return momentsRepo.getMoments().shared()
    }
}
