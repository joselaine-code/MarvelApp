package com.joselaine.marvelapp.presentation.viewmodels

import androidx.paging.PagingData
import com.joselaine.marvelapp.domain.usecase.GetCharactersUseCase
import com.joselaine.marvelapp.utils.MainCoroutineRule
import com.joselaine.marvelapp.utils.MarvelCharacterFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var charactersViewModel: CharactersViewModel

    private val characterFactory = MarvelCharacterFactory()
    private val pagingDataCharacters = PagingData.from(
        listOf(
            characterFactory.create(MarvelCharacterFactory.Hero.ThreeDMan),
            characterFactory.create(MarvelCharacterFactory.Hero.ABomb),
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runBlocking {
            // Arrange
            coEvery { getCharactersUseCase.invoke(any()) } returns flowOf(pagingDataCharacters)

            // Act
            val result = charactersViewModel.charactersPagingData("")

            // Assert
            assertNotNull(result.first())
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the usecase returns an exception`() =
        runBlocking<Unit> {
            // Arrange
            val exception = RuntimeException()
            coEvery { getCharactersUseCase.invoke(any()) } throws exception

            // Act
            charactersViewModel.charactersPagingData("")
        }
}
