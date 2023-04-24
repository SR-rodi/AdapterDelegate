package com.test.payloads.data.model

data class News(
    override val id: Int,
    val text: String,
    val isFavorite: Boolean,
) : DisplayPrint