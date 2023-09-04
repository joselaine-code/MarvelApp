package com.joselaine.marvelapp.data.datasources


import com.joselaine.marvelapp.data.db.dao.FavoriteDao
import com.joselaine.marvelapp.data.db.entity.FavoriteEntity
import com.joselaine.marvelapp.data.db.entity.toCharactersModel
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoritesLocalDataSource {

    override fun getAll(): Flow<List<MarvelCharacter>> {
        return favoriteDao.loadFavorites().map {
            it.toCharactersModel()
        }
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return favoriteDao.hasFavorite(characterId) != null
    }

    override suspend fun save(character: MarvelCharacter) {
        favoriteDao.insertFavorite(character.toFavoriteEntity())
    }

    override suspend fun delete(character: MarvelCharacter) {
        favoriteDao.deleteFavorite(character.toFavoriteEntity())
    }

    private fun MarvelCharacter.toFavoriteEntity() =
        FavoriteEntity(id.toInt(), name, imageUrl, description)
}