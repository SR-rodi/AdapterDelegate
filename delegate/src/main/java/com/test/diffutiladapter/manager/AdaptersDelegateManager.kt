package com.test.diffutiladapter.manager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.delegate.holder.Delegate
import com.test.diffutiladapter.adapter.AdapterDelegate

class AdaptersDelegateManager<I> {
    private val mapDelegates = mutableMapOf<Int, AdapterDelegate<I>>()

    fun addDelegate(delegate: AdapterDelegate<I>) {
        var viewType = mapDelegates.size
        if (mapDelegates[viewType] == null) mapDelegates[viewType] = delegate
        else {
            var isAdd = false
            while (!isAdd) {
                viewType++
                if (mapDelegates[viewType] == null) {
                    mapDelegates[viewType] = delegate
                    isAdd = true
                }
            }
        }
    }

    fun getItemViewType(item: I): Int {
        val delegatesCounter = mapDelegates.size
        for (index in 0 until delegatesCounter) {
            val listDelegate = mapDelegates.toList()
            if (listDelegate[index].second.isForViewType(
                    item)
            ) return listDelegate[index].first
        }
        @Suppress("UNREACHABLE_CODE")
        return throw Exception("AdapterDelegate not added")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        val delegate = mapDelegates[viewType] ?: throw Exception("AdapterDelegate not added")
        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(item:I, holder: Delegate.ViewHolder<I>) {
        val delegate =
            mapDelegates[holder.itemViewType] ?: throw Exception("AdapterDelegate not added")
        delegate.onBindViewHolder(holder, item)
    }

    fun onBindViewHolder(
        item: I, holder: Delegate.ViewHolder<I>, payloads: MutableList<Any>,
    ) {
        val delegate =
            mapDelegates[holder.itemViewType] ?: throw Exception("AdapterDelegate not added")
        delegate.onBindViewHolder(holder, item, payloads)
    }
}

