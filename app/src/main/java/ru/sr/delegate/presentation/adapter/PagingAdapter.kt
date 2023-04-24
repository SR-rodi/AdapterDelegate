package ru.sr.delegate.presentation.adapter

import ru.sr.adapter.PagingDelegateAdapter
import ru.sr.delegate.data.model.DisplayPrint


class PagingAdapter(onClickItem: (position: Int) -> Unit) :
    PagingDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}