package com.joselaine.marvelapp.di.qualifier

import com.joselaine.marvelapp.domain.usecase.base.AppCoroutinesDispatchers
import com.joselaine.marvelapp.domain.usecase.base.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun bindDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}