package com.test.payloads.presentation.adapter

import com.test.adapter.PagingDelegateAdapter
import com.test.payloads.data.model.DisplayPrint

class PagingAdapter(onClickItem: (position: Int) -> Unit) :
    PagingDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}