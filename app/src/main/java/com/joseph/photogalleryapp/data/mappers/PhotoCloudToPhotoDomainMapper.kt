package com.joseph.photogalleryapp.data.mappers

import com.joseph.photogalleryapp.common.Mapper
import com.joseph.photogalleryapp.data.cloud.models.PhotoCloud
import com.joseph.photogalleryapp.data.cloud.models.UrlsCloud
import com.joseph.photogalleryapp.domain.models.PhotoDomain
import com.joseph.photogalleryapp.domain.models.UrlsDomain

class PhotoCloudToPhotoDomainMapper : Mapper<PhotoCloud, PhotoDomain> {

    override fun map(from: PhotoCloud): PhotoDomain = from.run {
        PhotoDomain(
            id = id,
            urls = mapUrlsCloudToUrlsDomain(urls),
            height = height
        )
    }

    private fun mapUrlsCloudToUrlsDomain(cloudModel: UrlsCloud) = UrlsDomain(
        raw = cloudModel.raw,
        regular = cloudModel.regular,
        full = cloudModel.full,
        small = cloudModel.small,
        thumb = cloudModel.thumb
    )
}