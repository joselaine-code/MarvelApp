package com.joselaine.marvelapp.di

import com.joselaine.marvelapp.BuildConfig
import com.joselaine.marvelapp.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() : String = BuildConfig.BASE_URL
}
