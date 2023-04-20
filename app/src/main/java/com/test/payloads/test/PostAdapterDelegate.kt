package com.test.payloads.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.delegate.AdapterDelegate
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.News
import com.test.payloads.data.model.Post
import com.test.payloads.presentation.adapter.NewsViewHolder
import com.test.payloads.presentation.adapter.PostViewHolder

class PostAdapterDelegate(
    private val onClickItem: (position: Int) -> Unit,
) : AdapterDelegate<DisplayPrint> {

    override fun isForViewType(items: List<DisplayPrint>, position: Int): Boolean {
        return items[position] is Post
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PostViewHolder.create(parent, onClickItem)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<DisplayPrint>,
        position: Int,
    ) {
        (holder as PostViewHolder).bind((items[position] as Post))
    }
}

class NewsAdapterDelegate() : AdapterDelegate<DisplayPrint> {

    override fun isForViewType(items: List<DisplayPrint>, position: Int): Boolean {
        return items[position] is News
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<DisplayPrint>,
        position: Int,
    ) {
        (holder as NewsViewHolder).bind((items[position] as News))
    }
}