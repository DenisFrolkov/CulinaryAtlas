package com.den.culinaryatlas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val title: String,
    val ingredient: String,
    val action: String,
    @PrimaryKey(autoGenerate = true)
    val RecipeId: Long? = null
)
