package com.test.payloads.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.payloads.data.model.Post
import com.test.payloads.databinding.ItemBinding

class PostAdapter(private val onClickItem: (position: Int) -> Unit) :
    ListAdapter<Post, PostViewHolder>(PostDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder.create(parent, onClickItem)

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else holder.bind(payloads)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(private val binding: ItemBinding, onClickItem: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.favoriteIcon.setOnClickListener {
            onClickItem(adapterPosition)
        }
    }

    fun bind(item: Post) {
        binding.imagePost.setImageResource(item.poster)
        binding.title.text = item.text
        binding.favoriteIcon.isSelected = item.isFavorite
    }

    fun bind(payloads: MutableList<Any>) {
        binding.favoriteIcon.isSelected = payloads.last() as Boolean
    }

    companion object {
        fun create(parent: ViewGroup, onClickItem: (position: Int) -> Unit) = PostViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickItem
        )
    }

}

class PostDiff : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem

    override fun getChangePayload(oldItem: Post, newItem: Post): Any? {
        if (oldItem.isFavorite != newItem.isFavorite) return newItem.isFavorite
        return super.getChangePayload(oldItem, newItem)
    }

}