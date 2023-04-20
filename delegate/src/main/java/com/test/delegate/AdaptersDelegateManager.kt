package com.test.delegate

import android.util.SparseArray
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdaptersDelegateManager<I>(vararg delegates: AdapterDelegate<I>) {

    private val mapDelegates = mutableMapOf<Int, AdapterDelegate<I>>()
    val test: SparseArray<AdapterDelegate<I>> = SparseArray()

    init {
        delegates.forEach { delegate ->
            addDelegate(delegate)
        }
    }

    private fun addDelegate(delegate: AdapterDelegate<I>) {
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

    fun getItemViewType(items: List<I>, position: Int): Int {
        val delegatesCounter = mapDelegates.size
        for (index in 0 until delegatesCounter) {
            val listDelegate = mapDelegates.toList()
            if (listDelegate[index].second.isForViewType(
                    items,
                    position
                )
            ) return listDelegate[index].first
        }
        return throw Exception("AdapterDelegate not added")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val delegate = mapDelegates[viewType] ?: throw Exception("AdapterDelegate not added")

        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(items: List<I>, position: Int, holder: ViewHolder) {
        val delegate =
            mapDelegates[holder.itemViewType] ?: throw Exception("AdapterDelegate not added")

        delegate.onBindViewHolder(holder, items, position)

    }

}