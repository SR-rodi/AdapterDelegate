package com.test.payloads.presentation.adapter

import com.test.diffutiladapter.adapter.RecyclerViewDelegateAdapter
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.test.newsDelegate
import com.test.payloads.test.postDelegate

class BaseDelegateAdapter(onClickItem: (position: Int) -> Unit) :
    RecyclerViewDelegateAdapter<DisplayPrint>() {
    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}