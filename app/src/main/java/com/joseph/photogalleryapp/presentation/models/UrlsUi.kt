package com.joseph.photogalleryapp.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlsUi(
    val raw: String,
    var full: String,
    var regular: String,
    var small: String,
    var thumb: String
) : Parcelable {

    companion object {
        private val randomPhotoUrls =
            (0..100).map { "https://picsum.photos/id/${it + 10}/200/300" }

        fun previewUrals() = listOf(
            UrlsUi(
                raw = randomPhotoUrls.random(),
                full = randomPhotoUrls.random(),
                regular = randomPhotoUrls.random(),
                small = randomPhotoUrls.random(),
                thumb = randomPhotoUrls.random(),
            )
        )
    }
}