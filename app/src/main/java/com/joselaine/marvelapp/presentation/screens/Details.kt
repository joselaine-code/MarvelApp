package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import com.joselaine.marvelapp.presentation.composables.MarvelDetails
import com.joselaine.marvelapp.presentation.composables.MarvelError
import com.joselaine.marvelapp.presentation.composables.MarvelLoading
import com.joselaine.marvelapp.presentation.viewmodels.DetailsViewModel

@Composable
fun Details(navController: NavController) {
    val id = remember { mutableStateOf(0) }
    val arguments = navController.currentBackStackEntry?.arguments
    id.value = arguments?.getInt("id") ?: 0

    val viewModel = hiltViewModel<DetailsViewModel>()

    viewModel.getDetails(id.value)
    val state by viewModel.detailState.collectAsState()


    when (state) {
        is ResultStatus.Loading -> {
            MarvelLoading()
        }

        is ResultStatus.Success -> {
            val successState = state as ResultStatus.Success
            val character = successState.data
            MarvelDetails(character)
        }

        is ResultStatus.Error -> {
            MarvelError {
                viewModel.getDetails(id.value)
            }
        }
    }
}