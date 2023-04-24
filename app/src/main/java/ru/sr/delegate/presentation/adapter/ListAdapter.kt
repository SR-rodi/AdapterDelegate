package ru.sr.delegate.presentation.adapter

import ru.sr.adapter.ListDelegateAdapter
import ru.sr.delegate.data.model.DisplayPrint


class ListAdapter(onClickItem: (position: Int) -> Unit) :
    ListDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}


