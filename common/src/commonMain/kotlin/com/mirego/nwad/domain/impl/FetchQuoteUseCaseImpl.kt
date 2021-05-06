package com.mirego.nwad.domain.impl

import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.streams.reactive.onErrorReturn
import com.mirego.trikot.streams.reactive.shared
import com.mirego.nwad.domain.FetchQuoteUseCase
import com.mirego.nwad.repositories.QuoteRepository
import org.reactivestreams.Publisher

class FetchQuoteUseCaseImpl(private val quoteRepo: QuoteRepository) :
    FetchQuoteUseCase {
    override fun fetchQuote(): Publisher<String> {
        return quoteRepo.getQuotes()
            .map { it.firstOrNull()?.quote ?: "No quotes" }
            .onErrorReturn { it.message ?: "Unknown error occurred" }
            .shared()
    }
}
