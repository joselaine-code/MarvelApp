package com.joselaine.marvelapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joselaine.marvelapp.data.db.DataBaseConstants

@Entity(tableName = DataBaseConstants.CHARACTERS_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val autoId: Int = 0,
    @ColumnInfo(name = DataBaseConstants.CHARACTERS_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DataBaseConstants.CHARACTERS_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = DataBaseConstants.CHARACTERS_COLUMN_INFO_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = DataBaseConstants.CHARACTERS_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)
