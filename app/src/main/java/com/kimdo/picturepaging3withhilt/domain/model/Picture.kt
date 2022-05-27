package com.kimdo.picturepaging3withhilt.domain.model

data class Picture(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
)