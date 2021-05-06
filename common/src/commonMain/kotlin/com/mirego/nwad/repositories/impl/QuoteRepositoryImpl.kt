package com.mirego.nwad.repositories.impl

import com.mirego.trikot.http.RequestBuilder
import com.mirego.trikot.http.requestPublisher.DeserializableHttpRequestPublisher
import com.mirego.trikot.streams.reactive.ColdPublisher
import com.mirego.nwad.models.Quote
import com.mirego.nwad.repositories.QuoteRepository
import kotlinx.serialization.builtins.ListSerializer
import org.reactivestreams.Publisher

class QuoteRepositoryImpl() : QuoteRepository {
    override fun getQuotes(): Publisher<List<Quote>> {
        return ColdPublisher { cancellableManager ->
            val request = RequestBuilder().also {
                it.baseUrl = "https://breaking-bad-quotes.herokuapp.com"
                it.path = "/v1/quotes/5"
            }

            DeserializableHttpRequestPublisher(ListSerializer(Quote.serializer()), request).also {
                it.execute()
                cancellableManager.add(it)
            }
        }
    }
}
