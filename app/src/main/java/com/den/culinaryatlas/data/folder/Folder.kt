package com.den.culinaryatlas.data.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folder(
    val title: String,
    val recipeQuantity: String,
    @PrimaryKey(autoGenerate = true)
    val FolderId: Int? = null
)
