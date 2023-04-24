package com.test.payloads.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.test.delegate.holder.Delegate
import com.test.diffutiladapter.adapter.ListDelegateAdapter
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.News
import com.test.payloads.data.model.Post
import com.test.payloads.databinding.ItemNewsBinding
import com.test.payloads.databinding.ItemPostBinding
import com.test.payloads.test.DelegateNews
import com.test.payloads.test.DelegatePost


class PostAdapter( onClickItem: (position: Int) -> Unit) :
    ListDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate( DelegateNews())
        addDelegate(DelegatePost(onClickItem))
    }
}

class PostViewHolder(private val binding: ItemPostBinding, onClickItem: (position: Int) -> Unit) :
    Delegate.ViewHolder<Post>(binding.root) {

    init {
        binding.favoriteIcon.setOnClickListener {
            onClickItem(adapterPosition)
        }
    }

    override fun bind(item: Post) {
        binding.imagePost.setImageResource(item.poster)
        binding.title.text = item.text
        binding.favoriteIcon.isSelected = item.isFavorite
    }

    override fun bind(item: Post, payloads: MutableList<Any>) {
        binding.favoriteIcon.isSelected = payloads.last() as Boolean
    }

    companion object {
        fun create(parent: ViewGroup, onClickItem: (position: Int) -> Unit) = PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickItem
        )
    }

}

class NewsViewHolder(private val binding: ItemNewsBinding) :
    Delegate.ViewHolder<News>(binding.root) {

    override fun bind(item: News) {
        binding.title.text = item.text
    }

    companion object {
        fun create(parent: ViewGroup) = NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
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