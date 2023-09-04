package com.joselaine.marvelapp.domain.usecase

import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.repository.FavoritesRepository
import com.joselaine.marvelapp.domain.usecase.base.CoroutinesDispatchers
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoveFavoriteUseCase {

    suspend fun doWork(params: Params): ResultStatus<Unit>
    data class Params(
        val characterId: Int,
        val name: String,
        val imageUrl: String,
        val description: String
    )
}

class RemoveFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : RemoveFavoriteUseCase {
    override suspend fun doWork(params: RemoveFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoritesRepository.deleteFavorite(
                MarvelCharacter(
                    params.characterId.toString(),
                    params.name,
                    params.imageUrl,
                    params.description
                )
            )
            ResultStatus.Success(Unit)
        }
    }
}