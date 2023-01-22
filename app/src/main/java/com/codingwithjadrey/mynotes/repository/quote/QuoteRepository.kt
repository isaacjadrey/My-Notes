package com.codingwithjadrey.mynotes.repository.quote

import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.data.model.Quote
import com.codingwithjadrey.mynotes.util.Response

interface QuoteRepository {

    fun quoteId(): String

    suspend fun addQuote(quote: Quote)

    fun getQuote(result: (Response<List<Quote>>) -> Unit)
}