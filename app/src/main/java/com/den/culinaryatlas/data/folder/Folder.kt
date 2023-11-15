package com.den.culinaryatlas.data.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folder(
    val title: String,
    @PrimaryKey(autoGenerate = true)
    val FolderId: Int = 0
)
