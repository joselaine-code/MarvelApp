package com.joselaine.marvelapp.di

import com.joselaine.marvelapp.data.datasources.CharactersRemoteDataSourceImpl
 import com.joselaine.marvelapp.data.datasources.CharactersRemoteDataSource
import com.joselaine.marvelapp.data.datasources.FavoritesLocalDataSource
import com.joselaine.marvelapp.data.datasources.RoomFavoritesDataSource
import com.joselaine.marvelapp.domain.repository.CharactersRepository
import com.joselaine.marvelapp.data.repository.CharactersRepositoryImpl
import com.joselaine.marvelapp.data.repository.FavoritesRepositoryImpl
import com.joselaine.marvelapp.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindFavoritesLocalDataSource(dataSource: RoomFavoritesDataSource): FavoritesLocalDataSource

    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository
    @Binds
    fun bindRemoteDataSource(dataSource: CharactersRemoteDataSourceImpl):
            CharactersRemoteDataSource
}