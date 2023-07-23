package com.joselaine.marvelapp.domain.models

data class CharacterPaging(
    val offset: Int,
    val total: Int,
    val characters: List<MarvelCharacter>
)
