package com.joselaine.marvelapp.di

import com.joselaine.marvelapp.domain.usecase.AddFavoriteUseCase
import com.joselaine.marvelapp.domain.usecase.AddFavoriteUseCaseImpl
import com.joselaine.marvelapp.domain.usecase.CheckFavoriteUseCase
import com.joselaine.marvelapp.domain.usecase.CheckFavoriteUseCaseImpl
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCase
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCaseImpl
import com.joselaine.marvelapp.domain.usecase.GetCharactersUseCase
import com.joselaine.marvelapp.domain.usecase.GetCharactersUseCaseImpl
import com.joselaine.marvelapp.domain.usecase.GetFavoritesUseCase
import com.joselaine.marvelapp.domain.usecase.GetFavoritesUseCaseImpl
import com.joselaine.marvelapp.domain.usecase.RemoveFavoriteUseCase
import com.joselaine.marvelapp.domain.usecase.RemoveFavoriteUseCaseImpl
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
    
    @Binds
    fun bindsAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    fun bindsRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl): RemoveFavoriteUseCase

    @Binds
    fun bindsGetFavoritesUseCase(useCase: GetFavoritesUseCaseImpl): GetFavoritesUseCase

    @Binds
    fun bindsCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase
}