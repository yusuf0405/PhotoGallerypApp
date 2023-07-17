package com.joseph.photogalleryapp.data.cloud

import com.joseph.photogalleryapp.data.cloud.models.PhotoCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UnsplashService {

    @GET("photos")
    suspend fun fetchRandomPhotos(
        @Query("page") page: Int,
    ): Response<List<PhotoCloud>>
}

