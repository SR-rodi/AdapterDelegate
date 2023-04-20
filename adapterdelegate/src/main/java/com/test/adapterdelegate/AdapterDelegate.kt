package com.test.adapterdelegate

import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.FieldPosition

interface AdapterDelegate<I> {

    fun isForViewType(items:List<I>,position: Int):Boolean

    fun onCreateViewHolder(parent: ViewGroup):RecyclerView.ViewHolder

    fun onBindViewHolder(holder: ViewHolder,items: List<I>,position: Int)
}