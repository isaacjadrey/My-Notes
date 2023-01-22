package com.codingwithjadrey.mynotes.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.data.model.Quote
import com.codingwithjadrey.mynotes.repository.quote.QuoteRepository
import com.codingwithjadrey.mynotes.util.Response
import com.codingwithjadrey.mynotes.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {

    var quote: String? = null
    var author: String? = null
    var stateListener: StateListener? = null

    private val _quotes = MutableLiveData<Response<List<Quote>>>()
    val quotes: LiveData<Response<List<Quote>>> get() = _quotes

    fun addQuote() {
        stateListener?.started()
        viewModelScope.launch(Dispatchers.IO) {
            val quote = Quote(
                id = repository.quoteId(),
                quote = quote.toString(),
                author = author.toString()
            )
            repository.addQuote(quote)
            Handler(Looper.getMainLooper()).post { stateListener?.success() }
        }
    }

    fun getQuotes() {
        _quotes.value = Response.Loading
        repository.getQuote { _quotes.value = it }
    }
}