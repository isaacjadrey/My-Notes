package com.codingwithjadrey.mynotes.ui.note.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithjadrey.mynotes.data.model.Note
import com.codingwithjadrey.mynotes.databinding.FragmentNoteListBinding
import com.codingwithjadrey.mynotes.util.Response
import com.codingwithjadrey.mynotes.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by viewModels()
    private val adapter by lazy { NoteListAdapter(ArrayList<Note>()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            addNote.setOnClickListener {
                findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment())
            }
        }
        getNotes()
    }

    private fun getNotes() {
        binding.apply {
            notesRecyclerView.adapter = adapter
            notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            noteViewModel.getNotes()
            noteViewModel.notes.observe(viewLifecycleOwner) { data ->
                when (data) {
                    is Response.Loading -> {}
                    is Response.Failure -> {}
                    is Response.Success -> { adapter.setData(data.data.toMutableList())}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}