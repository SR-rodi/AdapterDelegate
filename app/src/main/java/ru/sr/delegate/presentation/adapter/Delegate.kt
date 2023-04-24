package ru.sr.delegate.presentation.adapter


import android.util.Log
import android.view.LayoutInflater
import ru.sr.adapter.adapterDelegate
import ru.sr.delegate.data.model.DisplayPrint
import ru.sr.delegate.data.model.News
import ru.sr.delegate.data.model.Post
import ru.sr.payloads.databinding.ItemNewsBinding
import ru.sr.payloads.databinding.ItemPostBinding


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

            Log.d("Kart","Kast")
            binding.imagePost.setImageResource(item.poster)
            binding.title.text = item.text
            binding.favoriteIcon.isSelected = item.isFavorite
        }

        bindForPayloads { payloads ->
            Log.e("Kart","Kast")
            binding.favoriteIcon.isSelected = payloads.last() as Boolean
        }
    }