package com.joseph.photogalleryapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joseph.photogalleryapp.common.theme.LargeSpacing

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
        Spacer(modifier = modifier.height(LargeSpacing))

        Button(onClick = onClick) {
            Text(
                text = "Retry",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }

}