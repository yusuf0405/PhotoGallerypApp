package com.joseph.photogalleryapp.presentation.gallery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.photogalleryapp.domain.usecases.FetchPhotosUseCase
import com.joseph.photogalleryapp.presentation.models.PhotoUi
import kotlinx.coroutines.launch

data class GalleryUiState(
    val photos: List<PhotoUi> = listOf(),
    val isLoading: Boolean = false,
    val refreshing: Boolean = false,
    val loadFinished: Boolean = false,
    val errorMessage: String? = null,
    val currentPage: Int = 1,
)

class GalleryViewModel(
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    var uiState by mutableStateOf(GalleryUiState())
    var currentPage = 1

    init {
        startLoadPhotos()
    }

    fun startLoadPhotos(
        forceLoad: Boolean = false
    ) {
        if (uiState.isLoading) return
        if (forceLoad) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            runCatching {
                val resultPhotos = fetchPhotosUseCase(currentPage)
                if (currentPage == 1) resultPhotos else uiState.photos + resultPhotos
            }.onSuccess { photos ->
                currentPage += 1
                uiState = uiState.copy(
                    currentPage = currentPage,
                    isLoading = false,
                    refreshing = false,
                    photos = photos,
                    loadFinished = photos.isEmpty(),
                )
            }.onFailure {
                uiState = uiState.copy(
                    isLoading = false,
                    refreshing = false,
                    errorMessage = it.localizedMessage ?: it.message ?: "Something went wrong!",
                    loadFinished = true
                )
            }
        }
    }
}