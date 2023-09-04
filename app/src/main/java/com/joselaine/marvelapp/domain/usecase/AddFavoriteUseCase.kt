package com.joselaine.marvelapp.domain.usecase

import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.repository.FavoritesRepository
import com.joselaine.marvelapp.domain.usecase.base.CoroutinesDispatchers
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddFavoriteUseCase {
    suspend fun doWork(params: Params): ResultStatus<Unit>
    data class Params(
        val characterId: Int,
        val name: String,
        val imageUrl: String,
        val description: String
    )
}

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : AddFavoriteUseCase {
    override suspend fun doWork(params: AddFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoritesRepository.saveFavorite(
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