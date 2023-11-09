package com.den.culinaryatlas.data.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val title: String,
    val ingredient: String,
    val action: String,
    val isAddingRecipe: Boolean,
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int = 0
)