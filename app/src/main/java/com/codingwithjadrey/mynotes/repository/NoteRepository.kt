package com.codingwithjadrey.mynotes.repository

import com.codingwithjadrey.mynotes.data.firebase.FirebaseData
import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.util.Response
import javax.inject.Inject

interface NoteRepository {
//    fun noteId() = firebaseData.noteId()
//
//    fun addNote(note: Note) = firebaseData.addNote(note)

    fun noteId(): String

    suspend fun addNoteToFirestore(note: Note)

    fun getNoteFromFirestore(result: (Response<List<Note>>) -> Unit)
}