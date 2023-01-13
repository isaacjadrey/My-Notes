package com.codingwithjadrey.mynotes.data.firebase

import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.util.Constant.NOTE_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class FirebaseData @Inject constructor(firestore: FirebaseFirestore) {

    private val notesCollection = firestore.collection(NOTE_COLLECTION)

    fun noteId() = notesCollection.document().id

    fun addNote(note: Note): Completable = Completable.create { emitter ->
        if (!emitter.isDisposed) {
            notesCollection.document(note.id)
                .set(note)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }
    }
}