package com.joselaine.marvelapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joselaine.marvelapp.data.db.entity.RemoteKey
import com.joselaine.marvelapp.data.db.DataBaseConstants

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM ${DataBaseConstants.REMOTE_KEYS_TABLE_NAME}")
    suspend fun remoteKey(): RemoteKey

    @Query("DELETE FROM ${DataBaseConstants.REMOTE_KEYS_TABLE_NAME}")
    suspend fun clearAll()
}