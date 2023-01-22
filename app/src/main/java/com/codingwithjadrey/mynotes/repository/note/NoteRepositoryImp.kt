package com.codingwithjadrey.mynotes.repository.note

import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.util.Constant.NOTE_COLLECTION
import com.codingwithjadrey.mynotes.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(private val firestore: FirebaseFirestore) :
    NoteRepository {

    private val ref = firestore.collection(NOTE_COLLECTION)
    val id = ref.document().id

    override fun noteId(): String = id

    override suspend fun addNoteToFirestore(note: Note) {
        ref.document(note.id).set(note).await()
    }

    override fun getNoteFromFirestore(result: (Response<List<Note>>) -> Unit) {
        ref.get()
            .addOnSuccessListener {
                val notes = arrayListOf<Note>()
                for (note in it) {
                    notes.add(note.toObject(Note::class.java))
                }
                result.invoke(Response.Success(notes))
            }
            .addOnFailureListener {
                result.invoke(Response.Failure(it.localizedMessage))
            }
    }
}