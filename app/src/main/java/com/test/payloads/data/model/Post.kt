package com.test.payloads.data.model

data class Post(
    override val id: Int,
    val text: String,
    val poster: Int,
    val isFavorite: Boolean,
) : DisplayPrint

data class News(
    override val id: Int,
    val text: String,
):DisplayPrint