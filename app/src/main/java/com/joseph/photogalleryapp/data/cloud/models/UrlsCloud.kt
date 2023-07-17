package com.joseph.photogalleryapp.data.cloud.models

import com.google.gson.annotations.SerializedName

data class UrlsCloud(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    var full: String,
    @SerializedName("regular")
    var regular: String,
    @SerializedName("small")
    var small: String,
    @SerializedName("thumb")
    var thumb: String
)