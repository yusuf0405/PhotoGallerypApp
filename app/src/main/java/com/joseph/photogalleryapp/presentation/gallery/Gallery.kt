package com.joseph.photogalleryapp.presentation.gallery

import androidx.compose.runtime.Composable
import com.joseph.photogalleryapp.presentation.destinations.PhotoDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun Gallery(
    navigator: DestinationsNavigator
) {
    val viewModel: GalleryViewModel = koinViewModel()

    GalleryScreen(
        uiState = viewModel.uiState,
        navigateToDetail = { id, page ->
            navigator.navigate(PhotoDetailDestination(id, page))
        },
        loadNextPhotos = { forceLoad ->
            viewModel.startLoadPhotos(forceLoad)
        }
    )
}