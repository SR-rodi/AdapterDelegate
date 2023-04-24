package com.test.diffutiladapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.test.delegate.holder.Delegate
import com.test.diffutiladapter.manager.AdaptersDelegateManager

abstract class ListDelegateAdapter<Interface>(
    diffUtil: DiffUtil.ItemCallback<Interface>,
) : ListAdapter<Interface, Delegate.ViewHolder<Interface>>(diffUtil) {

    private val delegateManager = AdaptersDelegateManager<Interface>()

    fun addDelegate(delegate: AdapterDelegate<Interface, >) {
        delegateManager.addDelegate(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): Delegate.ViewHolder<Interface> {
        @Suppress("UNCHECKED_CAST")
        return delegateManager.onCreateViewHolder(parent, viewType) as  Delegate.ViewHolder<Interface>
    }

    override fun onBindViewHolder(holder: Delegate.ViewHolder<Interface>, position: Int) {
        delegateManager.onBindViewHolder(getItem(position), holder)
    }

    override fun onBindViewHolder(
        holder: Delegate.ViewHolder<Interface>,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) onBindViewHolder(holder, position)
        else delegateManager.onBindViewHolder(getItem(position), holder, payloads)
    }
}