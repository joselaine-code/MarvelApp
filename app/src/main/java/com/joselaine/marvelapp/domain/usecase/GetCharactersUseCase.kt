package com.joselaine.marvelapp.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.joselaine.marvelapp.domain.repository.CharactersRepository
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.GetCharactersUseCase.GetCharactersParams
import com.joselaine.marvelapp.domain.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<MarvelCharacter>>

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersParams, MarvelCharacter>(),
    GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<MarvelCharacter>> {
        return charactersRepository.getCachedCharacters(params.query, params.pagingConfig)
    }
}