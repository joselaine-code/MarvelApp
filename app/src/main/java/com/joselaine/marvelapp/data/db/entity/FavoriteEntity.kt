package com.joselaine.marvelapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joselaine.marvelapp.data.db.DataBaseConstants
import com.joselaine.marvelapp.domain.models.MarvelCharacter

@Entity(tableName = DataBaseConstants.FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = DataBaseConstants.FAVORITES_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DataBaseConstants.FAVORITES_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = DataBaseConstants.FAVORITES_COLUMN_INFO_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = DataBaseConstants.FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)

fun List<FavoriteEntity>.toCharactersModel() = map {
    MarvelCharacter(it.id.toString(), it.name, it.description, it.imageUrl)
}
