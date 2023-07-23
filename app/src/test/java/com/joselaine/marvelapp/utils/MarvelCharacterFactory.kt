package com.joselaine.marvelapp.utils

import com.joselaine.marvelapp.domain.models.MarvelCharacter

class MarvelCharacterFactory {

    fun create(hero: Hero) = when (hero) {
        Hero.ThreeDMan -> MarvelCharacter(
            "123",
            "3-D Man",
            "Descrição do 3-D Man",
            "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        )
        Hero.ABomb -> MarvelCharacter(
            "345",
            "A-Bomb (HAS)",
            "Descrição do A-Bomb (HAS)",
            "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
        )
    }

    sealed class Hero {
        object ThreeDMan : Hero()
        object ABomb : Hero()
    }
}