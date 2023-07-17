package com.joseph.photogalleryapp.data.repositories

import com.joseph.photogalleryapp.common.DispatchersProvider
import com.joseph.photogalleryapp.data.cloud.client.UnsplashClientCreator
import com.joseph.photogalleryapp.data.mappers.PhotoCloudToPhotoDomainMapper
import com.joseph.photogalleryapp.domain.repositories.PhotosRepository
import com.joseph.photogalleryapp.domain.models.PhotoDomain
import kotlinx.coroutines.withContext
import java.lang.NullPointerException

class PhotosRepositoryImpl(
    unsplashClientCreator: UnsplashClientCreator,
    private val photoCloudToPhotoDomainMapper: PhotoCloudToPhotoDomainMapper,
    private val dispatchersProvider: DispatchersProvider,
) : PhotosRepository {

    private val service = unsplashClientCreator.createUnsplashService()

    override suspend fun fetchPhotos(page: Int): List<PhotoDomain> {
        val photoCloudList = withContext(dispatchersProvider.io()) {
            service.fetchRandomPhotos(page = page)
        }.body() ?: throw NullPointerException()
        return photoCloudList.map(photoCloudToPhotoDomainMapper::map)
    }
}