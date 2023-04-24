package ru.sr.delegate.presentation.adapter

import ru.sr.adapter.RecyclerViewDelegateAdapter
import ru.sr.delegate.data.model.DisplayPrint

class BaseDelegateAdapter(onClickItem: (position: Int) -> Unit) :
    RecyclerViewDelegateAdapter<DisplayPrint>() {
    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}