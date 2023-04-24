package com.test.delegate

import androidx.recyclerview.widget.RecyclerView
import com.test.delegate.holder.Delegate
import com.test.diffutiladapter.adapter.AdapterDelegate

abstract class ItemDelegate<Interface, Item : Interface, VH : Delegate.ViewHolder<Item>> :
    AdapterDelegate<Interface> {

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: Interface,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) onBindViewHolder(holder, item)
        else (holder as VH).bind(item as Item, payloads)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Interface) {
        (holder as VH).bind(item as Item)
    }

}