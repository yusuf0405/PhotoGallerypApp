package com.joseph.photogalleryapp.domain.mappers

import com.joseph.photogalleryapp.common.Mapper
import com.joseph.photogalleryapp.domain.models.PhotoDomain
import com.joseph.photogalleryapp.domain.models.UrlsDomain
import com.joseph.photogalleryapp.presentation.models.PhotoUi
import com.joseph.photogalleryapp.presentation.models.UrlsUi

class PhotoDomainToPhotoUiMapper : Mapper<PhotoDomain, PhotoUi> {

    override fun map(from: PhotoDomain): PhotoUi = from.run {
        PhotoUi(
            urls = mapUrlsDomainToUrlsUi(urls),
            id = id,
            height = (from.height / 30)
        )
    }

    fun map(from: PhotoDomain, page: Int): PhotoUi = from.run {
        PhotoUi(
            urls = mapUrlsDomainToUrlsUi(urls),
            id = id,
            height = (from.height / 30),
            page = page
        )
    }

    private fun mapUrlsDomainToUrlsUi(domainModel: UrlsDomain) = domainModel.run {
        UrlsUi(
            raw = raw,
            regular = regular,
            full = full,
            small = small,
            thumb = thumb
        )
    }
}