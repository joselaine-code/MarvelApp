package com.joselaine.marvelapp.data.datasources

import com.joselaine.marvelapp.data.MarvelApi
import com.joselaine.marvelapp.data.models.toCharacterModel
import com.joselaine.marvelapp.data.repository.CharactersRemoteDataSource
import com.joselaine.marvelapp.domain.models.CharacterPaging
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource {
    override suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries).data
        val characters = data.results.map {
            it.toCharacterModel()
        }

        return CharacterPaging(
            data.offset,
            data.total,
            characters
        )
    }

    override suspend fun fetchCharacter(id: Int): CharacterPaging {
        val data = marvelApi.getCharacter(id).data
        val characters = data.results.map {
            it.toCharacterModel()
        }

        return CharacterPaging(
            data.offset,
            data.total,
            characters
        )
    }
}