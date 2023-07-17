package com.joseph.photogalleryapp.domain.models

data class UrlsDomain(
    val raw: String,
    var full: String,
    var regular: String,
    var small: String,
    var thumb: String
)