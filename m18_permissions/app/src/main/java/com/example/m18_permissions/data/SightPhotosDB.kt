package com.example.m18_permissions.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.m18_permissions.entity.SightPhotos

@Entity(tableName = "sight_photo_DB")
data class SightPhotosDB (
    @PrimaryKey
    @ColumnInfo(name = "uri")
    override val uri: String,
    @ColumnInfo(name = "date")
    override val date: String
    ): SightPhotos
