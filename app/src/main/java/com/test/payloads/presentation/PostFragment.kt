package com.test.payloads.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.test.payloads.databinding.FragmentPostBinding
import com.test.payloads.presentation.adapter.BaseDelegateAdapter
import com.test.payloads.presentation.adapter.DisplayPrintAdapter
import com.test.payloads.presentation.adapter.PagingAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy { DisplayPrintAdapter(::onClickItem) }
    private val pagingAdapter by lazy { PagingAdapter(::onClickItem) }
    private val baseAdapter by lazy { BaseDelegateAdapter(::onClickItem) }
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

        pagerObserver()
        postObserver()
    }

    private fun postObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.post.collect { listPost ->
            listAdapter.submitList(listPost)
            baseAdapter.submitList(listPost)
            binding.recycler.adapter = baseAdapter

        }
    }

    private fun pagerObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.testPager.collect { data ->
            pagingAdapter.submitData(data)
        }
    }


    private fun onClickItem(itemPosition: Int) {
        viewModel.favoriteWorker(itemPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


