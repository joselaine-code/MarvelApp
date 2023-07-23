package com.joselaine.marvelapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joselaine.marvelapp.data.db.dao.CharacterDao
import com.joselaine.marvelapp.data.db.dao.RemoteKeyDao
import com.joselaine.marvelapp.data.db.entity.CharacterEntity
import com.joselaine.marvelapp.data.db.entity.RemoteKey

@Database(
    entities = [
        CharacterEntity::class,
        RemoteKey::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}