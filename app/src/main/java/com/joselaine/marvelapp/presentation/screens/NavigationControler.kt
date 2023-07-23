package com.joselaine.marvelapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joselaine.marvelapp.presentation.composables.MarvelBottomNavigation
import com.joselaine.marvelapp.presentation.composables.MarvelTopBar
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        val statusBarColor = RedMarvel
        systemUiController.setSystemBarsColor(
            color = statusBarColor
        )
    }

    Scaffold(
        backgroundColor = Color.White,
        bottomBar = { MarvelBottomNavigation(navController) },
        topBar = { MarvelTopBar() }
    ) { innerPadding ->
        ScreenController(modifier = Modifier.padding(innerPadding), navController)
    }
}