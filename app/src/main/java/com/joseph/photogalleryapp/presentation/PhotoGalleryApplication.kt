package com.joseph.photogalleryapp.presentation

import android.app.Application
import com.joseph.photogalleryapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotoGalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PhotoGalleryApplication)
            modules(appModules())
        }
    }
}