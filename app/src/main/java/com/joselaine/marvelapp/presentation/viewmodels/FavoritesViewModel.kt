package com.joselaine.marvelapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.GetFavoritesUseCase
import com.joselaine.marvelapp.domain.usecase.base.CoroutinesDispatchers
import com.joselaine.marvelapp.presentation.viewstate.FavoriteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val dispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _favoritesState =
        MutableStateFlow<FavoriteViewState>(FavoriteViewState.Loading)
    val favoritseState: StateFlow<FavoriteViewState> = _favoritesState
    fun getAllFavorites() {
        viewModelScope.launch(dispatchers.io()) {
            getFavoritesUseCase()
                .catch {
                    _favoritesState.emit(FavoriteViewState.Error)
                }
                .collect {
                    val items = it.map { character ->
                        MarvelCharacter(
                            id = character.id,
                            name = character.name,
                            description = character.description,
                            imageUrl = character.imageUrl
                        )
                    }

                    val uiState = if (items.isEmpty()) {
                        FavoriteViewState.Empty
                    } else FavoriteViewState.Success(items)

                    _favoritesState.emit(uiState)
                }
        }
    }
}