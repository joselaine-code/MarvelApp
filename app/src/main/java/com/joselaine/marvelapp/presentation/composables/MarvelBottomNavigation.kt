package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.joselaine.marvelapp.presentation.screens.Screens
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel

@Composable
fun MarvelBottomNavigation(navController: NavController) {
    val itemsInBottomNavigation = listOf(
        Screens.Home, Screens.Favorites
    )
    BottomNavigation(
        backgroundColor = RedMarvel,
        modifier = Modifier
            .height(70.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 16.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        itemsInBottomNavigation.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                        tint = if (currentRoute == screen.route) Color.White else Color.LightGray,
                        modifier = Modifier.size(30.dp)
                    )
                },
                selected = currentRoute == screen.route,
                label = {
                    Text(
                        text = screen.label,
                        color = if (currentRoute == screen.route) Color.White else Color.LightGray,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                },
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.popBackStack(screen.route, false)
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                        }
                    }
                },
                alwaysShowLabel = false,
                selectedContentColor = Color.White,
            )
        }
    }
}