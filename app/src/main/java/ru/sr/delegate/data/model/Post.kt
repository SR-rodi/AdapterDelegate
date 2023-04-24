package ru.sr.delegate.data.model

data class Post(
    override val id: Int,
    val text: String,
    val poster: Int,
    val isFavorite: Boolean,
) : DisplayPrint

