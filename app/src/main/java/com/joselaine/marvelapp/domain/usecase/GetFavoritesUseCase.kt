package com.joselaine.marvelapp.domain.usecase

import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.repository.FavoritesRepository
import com.joselaine.marvelapp.domain.usecase.base.CoroutinesDispatchers
import com.joselaine.marvelapp.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFavoritesUseCase {
    suspend operator fun invoke(params: Unit = Unit): Flow<List<MarvelCharacter>>
}

class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : FlowUseCase<Unit, List<MarvelCharacter>>(), GetFavoritesUseCase {

    override suspend fun createFlowObservable(params: Unit): Flow<List<MarvelCharacter>> {
        return withContext(dispatchers.io()) {
            favoritesRepository.getAll()
        }
    }
}