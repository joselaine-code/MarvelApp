package com.joselaine.marvelapp.data.datasources

import com.joselaine.marvelapp.data.MarvelApi
import com.joselaine.marvelapp.domain.models.CharacterPaging
import com.joselaine.marvelapp.utils.CharacterPagingFactory
import com.joselaine.marvelapp.utils.DataWrapperResponseFactory
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharactersRemoteDataSourceImplTest {

    private lateinit var marvelApi: MarvelApi
    private lateinit var dataSource: CharactersRemoteDataSourceImpl

    @Before
    fun setup() {
        marvelApi = mockk()
        dataSource = CharactersRemoteDataSourceImpl(marvelApi)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `fetchCharacters should return expected CharacterPaging`() = runBlocking {
        // Arrange
        val queries = mapOf("query" to "example")
        val expectedData = createMarvelData() // create a sample MarvelData object
        val expectedCharacterModels = expectedData.characters
        val expectedCharacterPaging = CharacterPaging(expectedData.offset, expectedData.total, expectedCharacterModels)

        coEvery { marvelApi.getCharacters(queries) } returns DataWrapperResponseFactory().create()

        // Act
        val result = dataSource.fetchCharacters(queries)

        // Assert
        assert(result == expectedCharacterPaging)
        coVerify { marvelApi.getCharacters(queries) }
    }

    @Test
    fun `fetchCharacter should return expected CharacterPaging`() = runBlocking {
        // Arrange
        val id = 123
        val expectedData = createMarvelData() // create a sample MarvelData object
        val expectedCharacterModels = expectedData.characters
        val expectedCharacterPaging = CharacterPaging(expectedData.offset, expectedData.total, expectedCharacterModels)

        coEvery { marvelApi.getCharacter(id) } returns DataWrapperResponseFactory().create()

        // Act
        val result = dataSource.fetchCharacter(id)

        // Assert
        assert(result == expectedCharacterPaging)
        coVerify { marvelApi.getCharacter(id) }
    }

    private fun createMarvelData(): CharacterPaging {
        return CharacterPagingFactory().create()
    }
}
