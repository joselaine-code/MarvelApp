package com.joselaine.marvelapp.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, var label: String, val icon: ImageVector) {
    object Home : Screens(RouteHome, "Home", Icons.Default.Home)
    object Favorites : Screens(RouteFavorites, "Favorites", Icons.Default.Star)
    object Details : Screens(RouteDetails, "", Icons.Default.Person)
}

const val RouteHome = "home"
const val RouteDetails = "details/{id}"
const val RouteFavorites = "favorites"
