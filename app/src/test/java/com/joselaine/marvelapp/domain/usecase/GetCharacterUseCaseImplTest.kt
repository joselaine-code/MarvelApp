package com.joselaine.marvelapp.domain.usecase

import com.joselaine.marvelapp.domain.repository.CharactersRepository
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import com.joselaine.marvelapp.utils.MainCoroutineRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharacterUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val characterRepository = mockk<CharactersRepository>()
    private lateinit var useCase: GetCharacterUseCaseImpl

    @Before
    fun setUp() {
        useCase = GetCharacterUseCaseImpl(characterRepository)
    }

    @After
    fun tearDown() {
        clearMocks(characterRepository)
    }

    @Test
    fun `invoke should return success result when characterRepository returns character`() =
        runBlocking {
            // Arrange
            val character = MarvelCharacter("1", "Iron Man", "description", "imageUrl")
            coEvery { characterRepository.getCharacter(1) } returns ResultStatus.Success(character)

            // Act
            val result = useCase.invoke(1)

            // Assert
            assertEquals(ResultStatus.Success(character), result)
        }
}
