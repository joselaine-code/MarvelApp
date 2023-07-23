package com.joselaine.marvelapp.utils

import com.joselaine.marvelapp.domain.models.CharacterPaging
import com.joselaine.marvelapp.domain.models.MarvelCharacter

class CharacterPagingFactory {

    fun create() = CharacterPaging(
        offset = 0,
        total = 2,
        characters = listOf(
            MarvelCharacter(
                id = "123",
                description = "Descrição do 3-D Man",
                name = "3-D Man",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            MarvelCharacter(
                id = "345",
                description = "Descrição do A-Bomb (HAS)",
                name = "A-Bomb (HAS)",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
            )
        )
    )
}