package com.joseph.photogalleryapp.domain.usecases

import com.joseph.photogalleryapp.presentation.models.PhotoUi

interface FetchPhotosUseCase {

    suspend operator fun invoke(page: Int): List<PhotoUi>

}