package com.codingwithjadrey.mynotes.ui.quote.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codingwithjadrey.mynotes.databinding.FragmentAddQuoteBinding
import com.codingwithjadrey.mynotes.util.StateListener
import com.codingwithjadrey.mynotes.util.gone
import com.codingwithjadrey.mynotes.util.makeToast
import com.codingwithjadrey.mynotes.util.visible
import com.codingwithjadrey.mynotes.viewmodels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddQuoteFragment: Fragment(), StateListener {

    private var _binding: FragmentAddQuoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddQuoteBinding.inflate(inflater, container, false)
        viewModel.stateListener = this
        binding.quoteModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            quoteButton.setOnClickListener {
                if (validation()){
                    viewModel.addQuote()
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true
        binding.apply {
            if (quoteTitle.text.toString().isEmpty()) {
                isValid = false
                makeToast("Enter Quote")
            }
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun started() {
        binding.progressBar.visible()
    }

    override fun success() {
        binding.progressBar.gone()
        makeToast("Quote add")
        findNavController().navigate(AddQuoteFragmentDirections.actionAddQuoteFragmentToQuoteListFragment())
    }

    override fun failure(message: String) {
        binding.progressBar.gone()
        makeToast("failed to save")
    }
}