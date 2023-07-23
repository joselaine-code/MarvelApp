package com.joselaine.marvelapp.utils

import com.joselaine.marvelapp.data.models.CharacterResponse
import com.joselaine.marvelapp.data.models.DataContainerResponse
import com.joselaine.marvelapp.data.models.DataWrapperResponse
import com.joselaine.marvelapp.data.models.ThumbnailResponse

class DataWrapperResponseFactory {
    fun create() : DataWrapperResponse {
        return DataWrapperResponse(
            copyright = "copyright",
            data = DataContainerResponse(
                offset = 0,
                total = 2,
                results = listOf(
                    CharacterResponse(
                        id = 123,
                        description = "Descrição do 3-D Man",
                        name = "3-D Man",
                        thumbnail = ThumbnailResponse(
                            path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                            extension = "jpg"
                        )
                    ),
                    CharacterResponse(
                        id = 345,
                        description = "Descrição do A-Bomb (HAS)",
                        name = "A-Bomb (HAS)",
                        thumbnail = ThumbnailResponse(
                            path = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
                            extension = "jpg"
                        )
                    )
                )
            )
        )
    }
}