package com.codingwithjadrey.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.repository.NoteRepository
import com.codingwithjadrey.mynotes.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    var title: String? = null
    var body: String? = null

    var stateListener: StateListener? = null
    private val disposables = CompositeDisposable()

    fun addNote() {
        stateListener?.started()
        val note = Note(
            id = repository.noteId(),
            title = title.toString(),
            body = body.toString()
        )
        val disposable = repository.addNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ stateListener?.success() }, { stateListener?.failure(it.message!!) })
        disposables.add(disposable)
    }
}