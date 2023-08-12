package com.joselaine.marvelapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import com.joselaine.marvelapp.presentation.composables.MarvelDetails
import com.joselaine.marvelapp.presentation.composables.MarvelError
import com.joselaine.marvelapp.presentation.composables.MarvelLoading
import com.joselaine.marvelapp.presentation.viewmodels.DetailsViewModel

@Composable
fun Details(navController: NavController) {
    val id = navController.currentBackStackEntry?.arguments?.getInt("id")

    val viewModel: DetailsViewModel = hiltViewModel()

    LaunchedEffect(id) {
        id?.let { viewModel.getDetails(it) }
    }

    val state by viewModel.detailState.collectAsState()

    when (state) {
        is ResultStatus.Loading -> {
            MarvelLoading()
        }

        is ResultStatus.Success -> {
            val character = (state as ResultStatus.Success).data
            MarvelDetails(character)
        }

        is ResultStatus.Error -> {
            MarvelError {
                id?.let { viewModel.getDetails(it) }
            }
        }
    }
}