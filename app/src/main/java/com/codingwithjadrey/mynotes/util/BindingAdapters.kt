package com.codingwithjadrey.mynotes.util

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.ui.note.list.NoteListFragmentDirections

class BindingAdapters {

    companion object {
        @BindingAdapter("android:updateNoteDetails")
        @JvmStatic
        fun CardView.updateNoteDetails(currentNote: Note) {
            this.setOnClickListener {
                val action = NoteListFragmentDirections
                    .actionNoteListFragmentToNoteDetailFragment(currentNote)
                this.findNavController().navigate(action)
            }
        }
    }
}