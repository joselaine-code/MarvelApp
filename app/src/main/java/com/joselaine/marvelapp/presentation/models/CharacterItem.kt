package com.joselaine.marvelapp.presentation.models

data class CharacterItem(
    val name: String,
    val imageUrl: String? = null,
    var isFavorite: Boolean = false
)