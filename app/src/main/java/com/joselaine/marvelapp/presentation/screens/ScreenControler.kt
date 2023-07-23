package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.joselaine.marvelapp.presentation.viewmodels.CharactersViewModel

@Composable
fun ScreenController(
    modifier: Modifier, navController: NavHostController
) {
    val viewModel = hiltViewModel<CharactersViewModel>()

    val charactersPagingData = viewModel.charactersPagingData("").collectAsLazyPagingItems()

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            Home(characterPagingData = charactersPagingData,
                onRetry = { charactersPagingData.retry() },
                clickOnCharacter = { navController.navigate("details/$it") })
        }
        composable(Screens.Favorites.route) {
            Favorites(modifier)
        }
        composable(Screens.Details.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )) {
            Details(navController)
        }
    }
}
