package ru.sr.delegate.data.model

data class News(
    override val id: Int,
    val text: String,
    val isFavorite: Boolean,
) : DisplayPrint