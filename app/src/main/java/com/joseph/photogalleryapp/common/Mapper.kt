package com.joseph.photogalleryapp.common

interface Mapper<From, To> {

    fun map(from: From): To
}