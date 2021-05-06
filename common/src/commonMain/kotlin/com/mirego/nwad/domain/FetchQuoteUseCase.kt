package com.mirego.nwad.domain

import org.reactivestreams.Publisher

interface FetchQuoteUseCase {
    fun fetchQuote(): Publisher<String>
}
