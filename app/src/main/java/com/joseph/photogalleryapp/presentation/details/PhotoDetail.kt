package com.joseph.photogalleryapp.presentation.details

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun PhotoDetail(
    navigator: DestinationsNavigator,
    photoId: String,
    page: Int
) {
    val viewModel: PhotoDetailViewModel = koinViewModel(
        parameters = { parametersOf(photoId, page) }
    )

    PhotoDetailsScreen(
        uiState = viewModel.uiState
    )
}