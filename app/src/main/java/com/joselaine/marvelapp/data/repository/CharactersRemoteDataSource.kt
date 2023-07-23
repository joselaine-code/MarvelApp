package com.joselaine.marvelapp.data.repository

import com.joselaine.marvelapp.domain.models.CharacterPaging

interface CharactersRemoteDataSource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun fetchCharacter(id: Int): CharacterPaging
}