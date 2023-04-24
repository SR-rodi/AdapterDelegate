package com.test.diffutiladapter.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.test.delegate.holder.Delegate
import com.test.diffutiladapter.manager.AdaptersDelegateManager

class PagingDelegateAdapter<Interface:Any>(
    diffUtil: DiffUtil.ItemCallback<Interface>
):PagingDataAdapter<Interface,Delegate.ViewHolder<Interface>>(diffUtil) {

    private val delegateManager = AdaptersDelegateManager<Interface>()

    fun addDelegate(delegate: AdapterDelegate<Interface, >) {
        delegateManager.addDelegate(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(snapshot().items[position])
    }

    override fun onBindViewHolder(holder: Delegate.ViewHolder<Interface>, position: Int) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): Delegate.ViewHolder<Interface> {
        TODO("Not yet implemented")
    }
}