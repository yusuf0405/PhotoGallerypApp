package com.joseph.photogalleryapp.presentation.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.joseph.photogalleryapp.common.theme.LargeSpacing
import com.joseph.photogalleryapp.common.theme.MediumSpacing
import com.joseph.photogalleryapp.presentation.components.ErrorScreen
import com.joseph.photogalleryapp.presentation.models.PhotoUi
import com.joseph.photogalleryapp.common.theme.PhotoGalleryAppTheme
import com.joseph.photogallerypapp.R

@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    uiState: GalleryUiState,
    loadNextPhotos: (Boolean) -> Unit,
    navigateToDetail: (String, Int) -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState.refreshing),
        onRefresh = { loadNextPhotos(true) },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PhotosList(
                uiState = uiState,
                loadNextPhotos = {
                    loadNextPhotos(it)
                },
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosList(
    modifier: Modifier = Modifier,
    uiState: GalleryUiState,
    loadNextPhotos: (Boolean) -> Unit,
    navigateToDetail: (String, Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(LargeSpacing),
        verticalItemSpacing = LargeSpacing,
        horizontalArrangement = Arrangement.spacedBy(LargeSpacing),
    ) {
        itemsIndexed(
            uiState.photos.toSet().toList(),
            key = { _, photo -> photo.id + photo.hashCode() },
        ) { index, photo ->
            PhotoItem(
                photo = photo,
                navigateToDetail = navigateToDetail
            )
            if (index >= uiState.photos.size - 1 && !uiState.isLoading && !uiState.loadFinished) {
                LaunchedEffect(key1 = Unit, block = { loadNextPhotos(false) })
            }

        }
        if (uiState.isLoading && uiState.photos.isNotEmpty()) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(LargeSpacing),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        if (uiState.errorMessage != null && !uiState.isLoading) {
            item(span = StaggeredGridItemSpan.FullLine) {
                ErrorScreen(
                    message = uiState.errorMessage,
                    onClick = { loadNextPhotos(true) }
                )
            }
        }
    }
}

@Composable
fun PhotoItem(
    modifier: Modifier = Modifier,
    photo: PhotoUi,
    navigateToDetail: (String, Int) -> Unit
) {
    AsyncImage(
        model = photo.urls.regular,
        contentDescription = null,
        modifier = modifier
            .height(photo.height.dp)
            .clip(RoundedCornerShape(MediumSpacing))
            .clickable { navigateToDetail(photo.id, photo.page) },
        contentScale = ContentScale.Crop,
        placeholder = if (isSystemInDarkTheme()) {
            painterResource(id = R.drawable.dark_image_place_holder)
        } else painterResource(id = R.drawable.light_image_place_holder)
    )
}

@Preview
@Composable
fun GalleryScreenPreview() {
    PhotoGalleryAppTheme {
        GalleryScreen(
            loadNextPhotos = { _ -> },
            navigateToDetail = { _, _ -> },
            uiState = GalleryUiState(
                isLoading = false,
                errorMessage = ""
            ),
        )
    }
}