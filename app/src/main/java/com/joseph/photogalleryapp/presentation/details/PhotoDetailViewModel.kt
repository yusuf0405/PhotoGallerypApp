package com.joseph.photogalleryapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.photogalleryapp.domain.usecases.FetchPhotosUseCase
import com.joseph.photogalleryapp.presentation.models.PhotoUi
import kotlinx.coroutines.launch

data class PhotoDetailUiState(
    val isLoading: Boolean = false,
    val currentPhotoIndex: Int? = null,
    val photos: List<PhotoUi> = emptyList(),
    val errorMessage: String? = null
)

class PhotoDetailViewModel(
    private val photoId: String,
    private val page: Int,
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    var uiState by mutableStateOf(PhotoDetailUiState())

    init {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            runCatching {
                fetchPhotosUseCase(page)
            }.onSuccess { photos ->
                var currentPhotoIndex = photos.map { it.id }.indexOf(photoId)
                if (currentPhotoIndex == -1) {
                    currentPhotoIndex = 1
                }
                uiState = uiState.copy(
                    isLoading = false,
                    photos = photos,
                    currentPhotoIndex = currentPhotoIndex
                )
            }.onFailure {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = it.localizedMessage ?: String(),
                )
            }
        }
    }
}