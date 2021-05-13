package com.mirego.nwad.repositories.impl

import com.mirego.trikot.http.RequestBuilder
import com.mirego.trikot.http.requestPublisher.DeserializableHttpRequestPublisher
import com.mirego.trikot.streams.reactive.ColdPublisher
import com.mirego.nwad.models.Moment
import com.mirego.nwad.repositories.MomentsRepository
import kotlinx.serialization.builtins.ListSerializer
import org.reactivestreams.Publisher

class MomentsRepositoryImpl() : MomentsRepository {
    override fun getMoments(): Publisher<List<Moment>> {
        return ColdPublisher { cancellableManager ->
            val request = RequestBuilder().also {
                it.baseUrl = "https://api.neverworkaday.com"
                it.path = "/organizations/mirego/moments"
            }

            DeserializableHttpRequestPublisher(ListSerializer(Moment.serializer()), request).also {
                it.execute()
                cancellableManager.add(it)
            }
        }
    }
}
