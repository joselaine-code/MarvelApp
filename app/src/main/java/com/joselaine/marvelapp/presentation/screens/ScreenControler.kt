package com.joselaine.marvelapp.presentation.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
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
    navController: NavHostController
) {
    val viewModel = hiltViewModel<CharactersViewModel>()

    val charactersPagingData = viewModel.charactersPagingData("").collectAsLazyPagingItems()

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }) {
            Home(characterPagingData = charactersPagingData,
                onRetry = { charactersPagingData.retry() },
                clickOnCharacter = { navController.navigate("details/$it") })
        }
        composable(Screens.Favorites.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            Favorites { navController.navigate("details/$it") }
        }
        composable(Screens.Details.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            Details(navController)
        }
    }
}

