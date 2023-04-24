package com.test.diffutiladapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<I> {

    fun isForViewType(item: I): Boolean

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: I)

    fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: I,
        payloads: MutableList<Any>,
    )
}