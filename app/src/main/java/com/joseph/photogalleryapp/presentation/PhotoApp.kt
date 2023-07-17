package com.joseph.photogalleryapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joseph.photogalleryapp.common.components.AppBar
import com.ramcosta.composedestinations.DestinationsNavHost

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PhotoApp() {
    val navHostController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.surface

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isSystemInDark
        )
    }

    Scaffold(
        topBar = {
            AppBar(navHostController = navHostController)
        }
    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }
}