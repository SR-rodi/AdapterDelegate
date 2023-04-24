package com.test.payloads.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.test.diffutiladapter.adapter.ListDelegateAdapter
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.Post
import com.test.payloads.test.newsDelegate
import com.test.payloads.test.postDelegate


class DisplayPrintAdapter(onClickItem: (position: Int) -> Unit) :
    ListDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}


class DisplayDiff : DiffUtil.ItemCallback<DisplayPrint>() {
    override fun areItemsTheSame(oldItem: DisplayPrint, newItem: DisplayPrint) =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DisplayPrint, newItem: DisplayPrint) =
        oldItem == newItem

    override fun getChangePayload(oldItem: DisplayPrint, newItem: DisplayPrint): Any? {
        if (oldItem is Post && newItem is Post)
            if (oldItem.isFavorite != newItem.isFavorite) return newItem.isFavorite
        return super.getChangePayload(oldItem, newItem)
    }
}