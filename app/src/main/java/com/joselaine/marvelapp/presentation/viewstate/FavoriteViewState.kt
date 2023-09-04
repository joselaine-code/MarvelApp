package com.joselaine.marvelapp.presentation.viewstate

import com.joselaine.marvelapp.domain.models.MarvelCharacter

sealed class FavoriteViewState {
    data object Loading : FavoriteViewState()
    data object Empty : FavoriteViewState()
    data class Success(val data: List<MarvelCharacter>) : FavoriteViewState()
    data object Error : FavoriteViewState()
}
