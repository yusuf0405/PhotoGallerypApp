package com.joseph.photogalleryapp.presentation.details

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.photogalleryapp.common.theme.ExtraLargeSpacing
import com.joseph.photogalleryapp.common.theme.LargeSpacing
import com.joseph.photogalleryapp.presentation.components.ErrorScreen
import com.joseph.photogalleryapp.presentation.models.PhotoUi
import com.joseph.photogallerypapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: PhotoDetailUiState,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.currentPhotoIndex != null) {
            val pagerState = rememberPagerState(initialPage = uiState.currentPhotoIndex)
            HorizontalPager(
                modifier = modifier
                    .padding(horizontal = ExtraLargeSpacing),
                pageCount = uiState.photos.size,
                state = pagerState
            ) { index ->
                ImagePage(
                    pagerState = pagerState,
                    index = index,
                    photos = uiState.photos.map { it.urls.full }
                )
            }
        } else CircularProgressIndicator()

        if (uiState.errorMessage != null && !uiState.isLoading) {
            ErrorScreen(
                message = uiState.errorMessage,
                onClick = { }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePage(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    index: Int,
    photos: List<String>

) {
    val matrix = remember { ColorMatrix() }

    val pagerOffset =
        (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction

    val imageSize by animateFloatAsState(
        targetValue = if (pagerOffset != 0.0f) 0.75f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    LaunchedEffect(key1 = imageSize) {
        val saturation = if (pagerOffset != 0.0f) 0f
        else 1f
        matrix.setToSaturation(saturation)
    }
    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(LargeSpacing))
            .height(600.dp)
            .graphicsLayer {
                scaleX = imageSize
                scaleY = imageSize
                shape = RoundedCornerShape(LargeSpacing)
                clip = true
            },
        model = photos[index],
        contentDescription = null,
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(matrix),
        placeholder = if (isSystemInDarkTheme()) {
            painterResource(id = R.drawable.dark_image_place_holder)
        } else painterResource(id = R.drawable.light_image_place_holder)
    )
}