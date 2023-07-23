package com.joselaine.marvelapp.data.models

import com.google.gson.annotations.SerializedName

data class DataContainerResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<CharacterResponse>
)
