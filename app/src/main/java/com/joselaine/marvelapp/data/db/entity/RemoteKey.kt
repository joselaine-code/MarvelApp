package com.joselaine.marvelapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joselaine.marvelapp.data.db.DataBaseConstants

@Entity(tableName = DataBaseConstants.REMOTE_KEYS_TABLE_NAME)
data class RemoteKey(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = DataBaseConstants.REMOTE_KEYS_COLUMN_INFO_OFFSET)
    val nextOffset: Int?
)
