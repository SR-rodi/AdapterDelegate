package ru.sr.delegate.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.sr.delegate.data.model.DisplayPrint
import ru.sr.delegate.data.model.Post

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