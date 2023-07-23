package com.joselaine.marvelapp.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joselaine.marvelapp.data.db.entity.CharacterEntity
import com.joselaine.marvelapp.data.db.DataBaseConstants

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM ${DataBaseConstants.CHARACTERS_TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM ${DataBaseConstants.CHARACTERS_TABLE_NAME}")
    suspend fun clearAll()
}