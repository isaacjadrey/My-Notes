package com.codingwithjadrey.mynotes.repository

import com.codingwithjadrey.mynotes.data.firebase.FirebaseData
import com.codingwithjadrey.mynotes.data.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val firebaseData: FirebaseData) {

    fun noteId() = firebaseData.noteId()

    fun addNote(note: Note) = firebaseData.addNote(note)
}