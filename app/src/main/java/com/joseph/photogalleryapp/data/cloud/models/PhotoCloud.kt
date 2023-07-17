package com.joseph.photogalleryapp.data.cloud.models


import com.google.gson.annotations.SerializedName

data class PhotoCloud(
    @SerializedName("urls")
    val urls: UrlsCloud,
    @SerializedName("id")
    val id: String,
    @SerializedName("height")
    val height: Int
)