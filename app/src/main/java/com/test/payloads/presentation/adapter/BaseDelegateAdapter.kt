package com.test.payloads.presentation.adapter

import com.test.adapter.RecyclerViewDelegateAdapter
import com.test.payloads.data.model.DisplayPrint

class BaseDelegateAdapter(onClickItem: (position: Int) -> Unit) :
    RecyclerViewDelegateAdapter<DisplayPrint>() {
    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}