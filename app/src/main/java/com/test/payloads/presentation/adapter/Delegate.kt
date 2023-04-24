package com.test.payloads.presentation.adapter


import android.view.LayoutInflater
import com.test.adapter.adapterDelegate
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.News
import com.test.payloads.data.model.Post
import com.test.payloads.databinding.ItemNewsBinding
import com.test.payloads.databinding.ItemPostBinding

fun newsDelegate() = adapterDelegate<DisplayPrint, News, ItemNewsBinding>({ parent ->
    ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
}) {
    bind {
        binding.title.text = item.text
    }
}

fun postDelegate(onClickItem: (position: Int) -> Unit) =
    adapterDelegate<DisplayPrint, Post, ItemPostBinding>({ parent ->
        ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }) {

        binding.favoriteIcon.setOnClickListener {
            onClickItem(bindingAdapterPosition)
        }
        bind {
            binding.imagePost.setImageResource(item.poster)
            binding.title.text = item.text
            binding.favoriteIcon.isSelected = item.isFavorite
        }

        bindForPayloads { payloads ->
            binding.favoriteIcon.isSelected = payloads.last() as Boolean
        }
    }