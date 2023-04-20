package com.test.payloads.data.model

data class Post(
    val id: Int,
    val text: String,
    val poster: Int,
    val isFavorite: Boolean,
) : DisplayPrint

data class News(
    val id: Int,
    val text: String,
):DisplayPrint