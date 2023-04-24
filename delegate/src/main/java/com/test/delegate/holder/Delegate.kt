package com.test.delegate.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface Delegate {
    abstract class ViewHolder<Item>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Item)
        open fun bind(item: Item, payloads: MutableList<Any>) {
            bind(item)
        }
    }
}

