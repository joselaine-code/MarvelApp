package com.joselaine.marvelapp.data.models

import com.google.gson.annotations.SerializedName
import com.joselaine.marvelapp.domain.models.MarvelCharacter

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)


fun CharacterResponse.toCharacterModel(): MarvelCharacter {
    return MarvelCharacter(
        id = this.id.toString(),
        name = this.name,
        description = this.description,
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
            .replace("http", "https")
    )
}