package com.test.payloads.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.test.payloads.databinding.FragmentPostBinding
import com.test.payloads.presentation.adapter.PostAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { PostAdapter(::onClickItem) }

    private val viewModel by viewModel<PostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPostBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        postObserver()
    }

    private fun postObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.post.collect { listPost ->
            adapter.submitList(listPost)
        }
    }

    private fun onClickItem(position: Int) {
        viewModel.favoriteWorker(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

