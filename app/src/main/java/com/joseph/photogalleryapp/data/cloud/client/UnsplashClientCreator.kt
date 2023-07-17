package com.joseph.photogalleryapp.data.cloud.client

import com.joseph.photogalleryapp.data.cloud.UnsplashService

interface UnsplashClientCreator {

    fun createUnsplashService(): UnsplashService
}