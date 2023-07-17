package com.joseph.photogalleryapp.presentation.models

import android.os.Parcelable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class PhotoUi(
    val urls: UrlsUi,
    val id: String,
    val height: Int,
    var page: Int = 1,
) : Parcelable {
    companion object {
        private val randomHeights = (100..300)

        fun previewPhotos() = listOf(
            PhotoUi(
                urls = UrlsUi.previewUrals().random(),
                id = UUID.randomUUID().toString(),
                height = randomHeights.random()
            ),
            PhotoUi(
                urls = UrlsUi.previewUrals().random(),
                id = UUID.randomUUID().toString(),
                height = randomHeights.random()

            ),
            PhotoUi(
                urls = UrlsUi.previewUrals().random(),
                id = UUID.randomUUID().toString(),
                height = randomHeights.random()
            ),
            PhotoUi(
                urls = UrlsUi.previewUrals().random(),
                id = UUID.randomUUID().toString(),
                height = randomHeights.random()
            ),
            PhotoUi(
                urls = UrlsUi.previewUrals().random(),
                id = UUID.randomUUID().toString(),
                height = randomHeights.random()
            ),
            PhotoUi(
                urls = UrlsUi.previewUrals().random(),
                id = UUID.randomUUID().toString(),
                height = randomHeights.random()
            ),
        )
    }
}