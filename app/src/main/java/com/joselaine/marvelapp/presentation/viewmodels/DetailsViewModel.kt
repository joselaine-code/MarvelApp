package com.joselaine.marvelapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCase
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow<ResultStatus<MarvelCharacter>>(ResultStatus.Loading)
    val detailState: StateFlow<ResultStatus<MarvelCharacter>> = _detailState

    fun getDetails(id: Int) {
        viewModelScope.launch {
            _detailState.value = getCharacterUseCase.invoke(id)
        }
    }

}