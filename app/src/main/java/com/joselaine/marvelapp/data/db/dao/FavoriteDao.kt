package com.joselaine.marvelapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joselaine.marvelapp.data.db.DataBaseConstants
import com.joselaine.marvelapp.data.db.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM ${DataBaseConstants.FAVORITES_TABLE_NAME}")
    fun loadFavorites(): Flow<List<FavoriteEntity>>
    @Query("SELECT * FROM ${DataBaseConstants.FAVORITES_TABLE_NAME} WHERE id = :characterId")
    suspend fun hasFavorite(characterId: Int): FavoriteEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}