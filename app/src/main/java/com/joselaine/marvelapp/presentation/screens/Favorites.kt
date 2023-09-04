package com.joselaine.marvelapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joselaine.marvelapp.presentation.composables.MarvelCard
import com.joselaine.marvelapp.presentation.composables.MarvelEmpty
import com.joselaine.marvelapp.presentation.composables.MarvelError
import com.joselaine.marvelapp.presentation.composables.MarvelLoading
import com.joselaine.marvelapp.presentation.models.CharacterItem
import com.joselaine.marvelapp.presentation.viewmodels.FavoritesViewModel
import com.joselaine.marvelapp.presentation.viewstate.FavoriteViewState

@Composable
fun Favorites(clickOnCharacter: (Int) -> Unit) {

    val viewModel: FavoritesViewModel = hiltViewModel()

    val state by viewModel.favoritseState.collectAsState()
    viewModel.getAllFavorites()

    when (state) {
        is FavoriteViewState.Loading -> {
            MarvelLoading()
        }

        is FavoriteViewState.Success -> {
            val character = (state as FavoriteViewState.Success).data
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(
                    count = character.size,
                ) { index ->
                    val actualCharacter = character[index]
                    MarvelCard(
                        modifier = Modifier.width(400.dp),
                        characterItem = CharacterItem(
                            name = actualCharacter.name,
                            imageUrl = actualCharacter.imageUrl
                        )
                    ) {
                        clickOnCharacter(actualCharacter.id.toInt())
                    }
                }
            }

        }

        is FavoriteViewState.Error -> {
            MarvelError {
                viewModel.getAllFavorites()
            }
        }

        is FavoriteViewState.Empty -> {
            MarvelEmpty()
        }
    }
}