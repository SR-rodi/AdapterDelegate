package com.test.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class DelegationAdapter<I>(
    private val delegateManager: AdaptersDelegateManager<I>,
    private val items: List<I>,
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        delegateManager.onBindViewHolder(items, position, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(items, position)
    }

    override fun getItemCount() = items.size
}

class RecyclerViewAdapter<I>(
   delegateManager: AdaptersDelegateManager<I>,
   items: List<I>,
) : DelegationAdapter<I>(delegateManager, items)