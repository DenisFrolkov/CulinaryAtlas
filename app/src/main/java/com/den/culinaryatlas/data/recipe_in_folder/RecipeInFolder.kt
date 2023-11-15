package com.den.culinaryatlas.data.recipe_in_folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeInFolder(
    var recipeId: Int?,
    val folderId: Int,
    @PrimaryKey(autoGenerate = true)
    val RecipeInFolderId: Int = 0
)