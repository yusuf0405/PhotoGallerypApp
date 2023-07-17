package com.joseph.photogalleryapp.common.components

import android.telecom.Call.Details
import android.view.Surface
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joseph.photogalleryapp.common.theme.AppBarHeight
import com.joseph.photogalleryapp.common.theme.ExtraLargeSpacing
import com.joseph.photogalleryapp.common.theme.MediumSpacing
import com.joseph.photogalleryapp.common.theme.PhotoGalleryAppTheme
import com.joseph.photogalleryapp.presentation.destinations.GalleryDestination
import com.joseph.photogalleryapp.presentation.destinations.PhotoDetailDestination
import com.joseph.photogallerypapp.R
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Preview
@Composable
fun AppBarPreview(
) {
    PhotoGalleryAppTheme {
        AppBar(
            navHostController = rememberNavController()
        )
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(AppBarHeight),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = modifier.padding(start = MediumSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = shouldShowNavigationIcon(currentDestination?.route)) {
                IconButton(
                    onClick = {
                        navHostController.navigateUp()
                    }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = modifier.width(ExtraLargeSpacing))
            }
            Text(
                text = stringResource(id = getAppBarTitle(currentDestination?.route)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(12.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@StringRes
private fun getAppBarTitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        GalleryDestination.route -> R.string.gallery_destination_title
        PhotoDetailDestination.route -> R.string.details_destination_title
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return currentDestinationRoute != GalleryDestination.route
}










