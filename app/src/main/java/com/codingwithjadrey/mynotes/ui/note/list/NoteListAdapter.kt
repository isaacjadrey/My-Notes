package com.codingwithjadrey.mynotes.ui.note.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.databinding.NoteItemBinding

class NoteListAdapter(private var list: List<Note>): RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setData(list: MutableList<Note>) {
        this.list = list
    }
}