package com.joseph.photogalleryapp.domain.usecases

import com.joseph.photogalleryapp.domain.mappers.PhotoDomainToPhotoUiMapper
import com.joseph.photogalleryapp.domain.repositories.PhotosRepository
import com.joseph.photogalleryapp.presentation.models.PhotoUi

class FetchPhotosUseCaseImpl(
    private val repository: PhotosRepository,
    private val photoDomainToPhotoUiMapper: PhotoDomainToPhotoUiMapper
) : FetchPhotosUseCase {

    override suspend fun invoke(page: Int): List<PhotoUi> {
        val photoDomainList = repository.fetchPhotos(page)
        return photoDomainList.map { photoDomainToPhotoUiMapper.map(it, page) }
    }
}