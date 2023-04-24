package com.test.payloads.presentation.adapter

import com.test.adapter.ListDelegateAdapter
import com.test.payloads.data.model.DisplayPrint


class ListAdapter(onClickItem: (position: Int) -> Unit) :
    ListDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}


