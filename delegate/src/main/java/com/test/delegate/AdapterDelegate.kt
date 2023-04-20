package com.test.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<I> {

    fun isForViewType(items:List<I>,position: Int):Boolean

    fun onCreateViewHolder(parent: ViewGroup):RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, items: List<I>, position: Int)
}