package com.mirego.nwad.repositories

import com.mirego.nwad.models.Quote
import org.reactivestreams.Publisher

interface QuoteRepository {
    fun getQuotes(): Publisher<List<Quote>>
}
