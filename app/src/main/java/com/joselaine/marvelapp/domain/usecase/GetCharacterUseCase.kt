package com.joselaine.marvelapp.domain.usecase

import com.joselaine.marvelapp.domain.repository.CharactersRepository
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import javax.inject.Inject

interface GetCharacterUseCase {
    suspend operator fun invoke(id: Int): ResultStatus<MarvelCharacter>
}

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterRepository: CharactersRepository
) : GetCharacterUseCase {
    override suspend operator fun invoke(id: Int): ResultStatus<MarvelCharacter> {
        return characterRepository.getCharacter(id)
    }
}