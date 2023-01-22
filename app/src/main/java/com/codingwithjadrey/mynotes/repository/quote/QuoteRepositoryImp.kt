package com.codingwithjadrey.mynotes.repository.quote

import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.data.model.Quote
import com.codingwithjadrey.mynotes.util.Constant.QUOTE_COLLECTION
import com.codingwithjadrey.mynotes.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuoteRepositoryImp @Inject constructor(private val firestore: FirebaseFirestore) :
    QuoteRepository {
    private val quoteRef = firestore.collection(QUOTE_COLLECTION)
    private val quoteId = quoteRef.document().id

    override fun quoteId(): String = quoteId

    override suspend fun addQuote(quote: Quote) {
        quoteRef.document(quote.id).set(quote).await()
    }

    override fun getQuote(result: (Response<List<Quote>>) -> Unit) {
        quoteRef.get()
            .addOnSuccessListener {
                val quotes = mutableListOf<Quote>()
                for (quote in it) {
                    quotes.add(quote.toObject(Quote::class.java))
                }
                result.invoke(Response.Success(quotes))
            }
            .addOnFailureListener {
                result.invoke(Response.Failure(it.localizedMessage))
            }
    }
}