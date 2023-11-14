package com.den.culinaryatlas.data.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    var title: String,
    var ingredient: String,
    var action: String,
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int = 0
)