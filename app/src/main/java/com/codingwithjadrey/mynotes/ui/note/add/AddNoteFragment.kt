package com.codingwithjadrey.mynotes.ui.note.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codingwithjadrey.mynotes.R
import com.codingwithjadrey.mynotes.databinding.FragmentAddNoteBinding
import com.codingwithjadrey.mynotes.util.StateListener
import com.codingwithjadrey.mynotes.util.gone
import com.codingwithjadrey.mynotes.util.makeToast
import com.codingwithjadrey.mynotes.util.visible
import com.codingwithjadrey.mynotes.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(), StateListener {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        binding.addNoteModel = noteViewModel
        noteViewModel.stateListener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
    }

    private fun setupMenu() {
        binding.apply {
            toolbarAddNote.setOnClickListener {
                findNavController().popBackStack()
            }

            toolbarAddNote.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_save -> {
                        noteViewModel.addNote()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun started() {
        binding.progressView.visible()
    }

    override fun success() {
        binding.progressView.gone()
        makeToast("added note successfully")
    }

    override fun failure(message: String) {
        binding.progressView.gone()
        makeToast(message)
    }
}