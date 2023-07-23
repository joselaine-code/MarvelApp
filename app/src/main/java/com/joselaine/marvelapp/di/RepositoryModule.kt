package com.joselaine.marvelapp.di

import com.joselaine.marvelapp.data.datasources.CharactersRemoteDataSourceImpl
import com.joselaine.marvelapp.data.repository.CharactersRemoteDataSource
import com.joselaine.marvelapp.domain.repository.CharactersRepository
import com.joselaine.marvelapp.data.repository.CharactersRepositoryImpl
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
    fun bindRemoteDataSource(dataSource: CharactersRemoteDataSourceImpl):
            CharactersRemoteDataSource
}