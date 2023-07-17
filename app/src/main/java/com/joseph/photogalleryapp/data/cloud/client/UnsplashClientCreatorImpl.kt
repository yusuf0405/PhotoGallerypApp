package com.joseph.photogalleryapp.data.cloud.client

import com.joseph.photogalleryapp.data.cloud.UnsplashService
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UnsplashClientCreatorImpl : UnsplashClientCreator {

    override fun createUnsplashService(): UnsplashService =
        createRetrofit().create(UnsplashService::class.java)


    private fun createRetrofit(): Retrofit {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor())
            .addInterceptor(httpLoggingInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL_UNSPLASH)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private fun requestInterceptor() = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .cacheControl(CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build())
            .addHeader("Authorization", "Client-ID $unsplash_access_key")
            .build()
        return@Interceptor chain.proceed(request)
    }


    private companion object {
        const val unsplash_access_key = "SutVFBh-QpWWpFUwDf0n-qm3Gz2wfJZ65BtZf00DQ6I"
        const val BASE_URL_UNSPLASH = "https://api.unsplash.com/"
    }
}