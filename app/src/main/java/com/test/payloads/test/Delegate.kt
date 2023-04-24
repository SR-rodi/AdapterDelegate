package com.test.payloads.test

import android.view.ViewGroup
import com.test.delegate.ItemDelegate
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.News
import com.test.payloads.data.model.Post
import com.test.payloads.presentation.adapter.NewsViewHolder
import com.test.payloads.presentation.adapter.PostViewHolder

class DelegatePost(
    private val onClickItem: (position: Int) -> Unit,
) : ItemDelegate<DisplayPrint, Post, PostViewHolder>() {

    override fun isForViewType(item: DisplayPrint): Boolean {
        return item is Post
    }

    override fun onCreateViewHolder(parent: ViewGroup): PostViewHolder {
        return PostViewHolder.create(parent, onClickItem)
    }
}

class DelegateNews : ItemDelegate<DisplayPrint, News, NewsViewHolder>() {

    override fun isForViewType(item: DisplayPrint): Boolean {
        return item is News
    }

    override fun onCreateViewHolder(parent: ViewGroup): NewsViewHolder {
        return NewsViewHolder.create(parent)
    }
}
