package com.test.diffutiladapter.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.diffutiladapter.manager.AdaptersDelegateManager

abstract class PagingDelegateAdapter<Interface : Any>(
    diffUtil: DiffUtil.ItemCallback<Interface>,
) : PagingDataAdapter<Interface, RecyclerView.ViewHolder>(diffUtil) {

    private val delegateManager = AdaptersDelegateManager<Interface>()

    fun addDelegate(delegate: AdapterDelegate<Interface>) {
        delegateManager.addDelegate(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(snapshot().items[position])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { delegateManager.onBindViewHolder(it, holder) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }
}