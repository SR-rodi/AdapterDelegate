package com.test.payloads.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.payloads.data.Repository
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: Repository,
) : ViewModel() {

    init {
        getPosts()
    }

    val testPager = repository.getPagingData().cachedIn(viewModelScope)

    private val _posts = MutableStateFlow(emptyList<DisplayPrint>())
    val post = _posts.asStateFlow()

    private fun getPosts() = viewModelScope.launch {
        _posts.value = repository.getList()

    }

    fun favoriteWorker(position: Int) = viewModelScope.launch {

        val list = _posts.value.toMutableList() as MutableList<Post>
        repository.favoriteWorker(list[position].id)
        list[position] = list[position].copy(isFavorite = !list[position].isFavorite)
        _posts.value = list
    }

}