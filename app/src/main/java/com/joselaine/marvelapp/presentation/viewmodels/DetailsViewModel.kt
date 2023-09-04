package com.joselaine.marvelapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.AddFavoriteUseCase
import com.joselaine.marvelapp.domain.usecase.CheckFavoriteUseCase
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCase
import com.joselaine.marvelapp.domain.usecase.RemoveFavoriteUseCase
import com.joselaine.marvelapp.domain.usecase.base.CoroutinesDispatchers
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val dispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _detailState = MutableStateFlow<ResultStatus<MarvelCharacter>>(ResultStatus.Loading)
    val detailState: StateFlow<ResultStatus<MarvelCharacter>> = _detailState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun getDetails(id: Int) {
        viewModelScope.launch(dispatchers.io()) {
            checkFavorite(id)
            _detailState.value = getCharacterUseCase.invoke(id)
        }
    }

    fun checkFavorite(id: Int) {
        viewModelScope.launch(dispatchers.io()) {
            checkFavoriteUseCase.invoke(CheckFavoriteUseCase.Params(id)).collect { actualStatus ->
                if (actualStatus is ResultStatus.Success) {
                    _isFavorite.emit(actualStatus.data)
                }
            }
        }
    }

    fun addFavorite() {
        viewModelScope.launch(dispatchers.io()) {
            val character = (_detailState.value as ResultStatus.Success<MarvelCharacter>).data
            addFavoriteUseCase.doWork(
                AddFavoriteUseCase.Params(
                    characterId = character.id.toInt(),
                    name = character.name,
                    description = character.description,
                    imageUrl = character.imageUrl
                )
            )
            checkFavorite(id = character.id.toInt())
        }
    }


    fun removeFavorite() {
        viewModelScope.launch(dispatchers.io()) {
            val character = (_detailState.value as ResultStatus.Success<MarvelCharacter>).data
            removeFavoriteUseCase.doWork(
                RemoveFavoriteUseCase.Params(
                    character.id.toInt(),
                    character.name,
                    character.imageUrl,
                    character.imageUrl
                )
            )
            checkFavorite(id = character.id.toInt())
        }
    }
}