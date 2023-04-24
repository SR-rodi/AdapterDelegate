package com.test.payloads.data.model

data class Post(
    override val id: Int,
    val text: String,
    val poster: Int,
    override val isFavorite: Boolean,
) : DisplayPrint

data class News(
    override val id: Int,
    val text: String,
    override val isFavorite: Boolean,
) : DisplayPrint