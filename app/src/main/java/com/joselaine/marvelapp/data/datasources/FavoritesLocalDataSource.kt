package com.joselaine.marvelapp.data.datasources

import com.joselaine.marvelapp.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {
    fun getAll(): Flow<List<MarvelCharacter>>
    suspend fun isFavorite(characterId: Int): Boolean
    suspend fun save(character: MarvelCharacter)
    suspend fun delete(character: MarvelCharacter)
}