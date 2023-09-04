package com.joselaine.marvelapp.data.repository

import com.joselaine.marvelapp.data.datasources.FavoritesLocalDataSource
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
) : FavoritesRepository {
    override fun getAll(): Flow<List<MarvelCharacter>> {
        return favoritesLocalDataSource.getAll()
    }
    override suspend fun isFavorite(characterId: Int): Boolean {
        return favoritesLocalDataSource.isFavorite(characterId)
    }
    override suspend fun saveFavorite(character: MarvelCharacter) {
        favoritesLocalDataSource.save(character)
    }
    override suspend fun deleteFavorite(character: MarvelCharacter) {
        favoritesLocalDataSource.delete(character)
    }
}