package com.joselaine.marvelapp.domain.repository

import com.joselaine.marvelapp.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAll(): Flow<List<MarvelCharacter>>
    suspend fun isFavorite(characterId: Int): Boolean
    suspend fun saveFavorite(character: MarvelCharacter)
    suspend fun deleteFavorite(character: MarvelCharacter)
}