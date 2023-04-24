package com.test.payloads.presentation.adapter

import com.test.diffutiladapter.adapter.PagingDelegateAdapter
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.test.newsDelegate
import com.test.payloads.test.postDelegate

class PagingAdapter(onClickItem: (position: Int) -> Unit) :
    PagingDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}