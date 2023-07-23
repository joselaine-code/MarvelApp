package com.joselaine.marvelapp.data

import com.joselaine.marvelapp.data.models.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path(value = "id")
        id: Int
    ): DataWrapperResponse
}