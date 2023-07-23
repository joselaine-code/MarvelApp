package com.joselaine.marvelapp.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joselaine.marvelapp.domain.models.MarvelCharacter

class PagingSourceFactory {
    @Suppress("MagicNumber")
    fun create(heroes: List<MarvelCharacter>) = object : PagingSource<Int, MarvelCharacter>() {
        override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int = 1
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
            return LoadResult.Page(
                heroes,
                null,
                20
            )
        }
    }
}