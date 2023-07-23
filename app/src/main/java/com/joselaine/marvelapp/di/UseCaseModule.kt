package com.joselaine.marvelapp.di

import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCase
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCaseImpl
import com.joselaine.marvelapp.domain.usecase.GetCharactersUseCase
import com.joselaine.marvelapp.domain.usecase.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindsCharacterUseCase(useCase: GetCharacterUseCaseImpl): GetCharacterUseCase
}