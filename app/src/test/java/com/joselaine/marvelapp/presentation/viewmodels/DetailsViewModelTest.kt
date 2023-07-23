package com.joselaine.marvelapp.presentation.viewmodels

import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.domain.usecase.GetCharacterUseCase
import com.joselaine.marvelapp.domain.usecase.base.ResultStatus
import com.joselaine.marvelapp.utils.MainCoroutineRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var getCharacterUseCase: GetCharacterUseCase
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        getCharacterUseCase = mockk()
        viewModel = DetailsViewModel(getCharacterUseCase)
    }

    @After
    fun cleanup() {
        clearAllMocks()
    }

    @Test
    fun `getDetails should update detailState with Success result`() = runBlocking {
        // Arrange
        val id = 1
        val character = MarvelCharacter("1", "Iron Man", "description", "imageUrl")
        val successResult = ResultStatus.Success(character)
        coEvery { getCharacterUseCase.invoke(id) } returns successResult

        // Act
        viewModel.getDetails(id)

        // Assert
        assert(viewModel.detailState.value is ResultStatus.Success)
        assert((viewModel.detailState.value as ResultStatus.Success).data == character)

        coVerify { getCharacterUseCase.invoke(id) }
    }

    @Test
    fun `getDetails should update detailState with Error result`() = runBlocking {
        // Arrange
        val id = 1
        val errorResult = ResultStatus.Error(Exception())
        coEvery { getCharacterUseCase.invoke(id) } returns errorResult

        // Act
        viewModel.getDetails(id)

        // Assert
        assert(viewModel.detailState.value is ResultStatus.Error)

        coVerify { getCharacterUseCase.invoke(id) }
    }
}
