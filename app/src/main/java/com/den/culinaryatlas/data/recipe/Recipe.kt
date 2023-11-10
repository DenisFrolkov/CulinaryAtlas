package com.den.culinaryatlas.data.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val title: String,
    val ingredient: String,
    val action: String,
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int = 0
)