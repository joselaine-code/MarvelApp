package com.joselaine.marvelapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.joselaine.marvelapp.data.db.AppDatabase
import com.joselaine.marvelapp.domain.repository.CharactersRepository
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import com.joselaine.marvelapp.domain.paging.CharactersPagingSource
import com.joselaine.marvelapp.domain.paging.CharactersRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val database: AppDatabase
) : CharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, MarvelCharacter> {
        return CharactersPagingSource(remoteDataSource, query)
    }


    @Suppress("TooGenericExceptionCaught")
    override suspend fun getCharacter(id: Int): ResultStatus<MarvelCharacter> {
        return try {
            ResultStatus.Success(remoteDataSource.fetchCharacter(id).characters[0])
        } catch (e: Exception) {
            ResultStatus.Error(e)
        }
    }

    override fun getCachedCharacters(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = CharactersRemoteMediator(query, database, remoteDataSource)
        ) {
            database.characterDao().pagingSource()
        }.flow.map { pagingData ->
            pagingData.map {
                MarvelCharacter(
                    it.id.toString(),
                    it.name,
                    it.description,
                    it.imageUrl
                )
            }
        }
    }
}