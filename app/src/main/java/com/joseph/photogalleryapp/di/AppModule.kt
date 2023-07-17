package com.joseph.photogalleryapp.di

import com.joseph.photogalleryapp.common.DispatchersProvider
import com.joseph.photogalleryapp.data.cloud.client.UnsplashClientCreator
import com.joseph.photogalleryapp.data.cloud.client.UnsplashClientCreatorImpl
import com.joseph.photogalleryapp.data.mappers.PhotoCloudToPhotoDomainMapper
import com.joseph.photogalleryapp.data.repositories.PhotosRepositoryImpl
import com.joseph.photogalleryapp.domain.mappers.PhotoDomainToPhotoUiMapper
import com.joseph.photogalleryapp.domain.repositories.PhotosRepository
import com.joseph.photogalleryapp.domain.usecases.FetchPhotosUseCase
import com.joseph.photogalleryapp.domain.usecases.FetchPhotosUseCaseImpl
import com.joseph.photogalleryapp.presentation.details.PhotoDetailViewModel
import com.joseph.photogalleryapp.presentation.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModules() = dataModule + appModule + domainModule

val appModule = module {
    viewModel {
        GalleryViewModel(
            fetchPhotosUseCase = get()
        )
    }
    viewModel { params ->
        PhotoDetailViewModel(
            fetchPhotosUseCase = get(),
            photoId = params.get(),
            page = params.get()
        )
    }
    factory<DispatchersProvider> { DispatchersProvider.Base() }
}

val dataModule = module {
    single<UnsplashClientCreator> { UnsplashClientCreatorImpl() }
    factory { PhotoCloudToPhotoDomainMapper() }
    factory<PhotosRepository> {
        PhotosRepositoryImpl(
            unsplashClientCreator = get(),
            dispatchersProvider = get(),
            photoCloudToPhotoDomainMapper = get()
        )
    }
}

val domainModule = module {
    factory { PhotoDomainToPhotoUiMapper() }
    factory<FetchPhotosUseCase> { FetchPhotosUseCaseImpl(get(), get()) }
}
