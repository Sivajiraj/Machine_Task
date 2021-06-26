package com.appentus.machinetask.model.response

import java.io.Serializable


data class ImageResponseItem (
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
): Serializable