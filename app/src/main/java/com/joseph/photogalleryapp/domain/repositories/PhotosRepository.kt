package com.joseph.photogalleryapp.domain.repositories

import com.joseph.photogalleryapp.domain.models.PhotoDomain

interface PhotosRepository {

    suspend fun fetchPhotos(page: Int): List<PhotoDomain>
}