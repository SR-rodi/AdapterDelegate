package ru.sr.delegate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.delegate.presentation.adapter.BaseDelegateAdapter
import ru.sr.delegate.presentation.adapter.ListAdapter
import ru.sr.delegate.presentation.adapter.PagingAdapter
import ru.sr.payloads.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy { ListAdapter(::onClickItem) }
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
        binding.recycler.adapter = listAdapter
        //pagerObserver()
        postObserver()
    }

    private fun postObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.post.collect { listPost ->
            listAdapter.submitList(listPost)
        }
    }

/*    private fun pagerObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.testPager.collect { data ->
            pagingAdapter.submitData(data)
        }
    }*/


    private fun onClickItem(itemPosition: Int) {
        viewModel.favoriteWorker(itemPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


