package com.codingwithjadrey.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import com.codingwithjadrey.mynotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

    var title: String? = null
    var body: String? = null
}