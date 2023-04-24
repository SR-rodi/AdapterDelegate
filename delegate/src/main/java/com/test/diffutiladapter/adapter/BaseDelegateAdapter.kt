package com.test.diffutiladapter.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.diffutiladapter.manager.AdaptersDelegateManager

internal abstract class BaseDelegateAdapter<Item, VH : ViewHolder> : RecyclerView.Adapter<VH>() {

    private val delegateManager = AdaptersDelegateManager<Item>()

    fun addDelegate(delegate: AdapterDelegate<Item>) {
        delegateManager.addDelegate(delegate)
    }
}