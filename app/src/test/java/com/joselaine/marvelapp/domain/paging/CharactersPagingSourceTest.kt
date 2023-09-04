package com.joselaine.marvelapp.domain.paging

import androidx.paging.PagingSource
import com.joselaine.marvelapp.data.datasources.CharactersRemoteDataSource
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.utils.CharacterPagingFactory
import com.joselaine.marvelapp.utils.MainCoroutineRule
import com.joselaine.marvelapp.utils.MarvelCharacterFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var remoteDataSource: CharactersRemoteDataSource

    private val characterPagingFactory = CharacterPagingFactory()

    private val characterFactory = MarvelCharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return a success load result when load is called`() = runBlocking {
        // Arrange
        coEvery { remoteDataSource.fetchCharacters(any()) }
            .returns(characterPagingFactory.create())

        // Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        // Assert
        val expected = listOf(
            characterFactory.create(MarvelCharacterFactory.Hero.ThreeDMan),
            characterFactory.create(MarvelCharacterFactory.Hero.ABomb),
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 20
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runBlocking {
        // Arrange
        val exception = RuntimeException()
        coEvery { remoteDataSource.fetchCharacters(any()) }
            .throws(exception)

        // Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // Assert
        assertEquals(
            PagingSource.LoadResult.Error<Int, MarvelCharacter>(exception),
            result
        )
    }
}
