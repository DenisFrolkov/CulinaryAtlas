package com.den.culinaryatlas.data

import androidx.compose.ui.graphics.painter.Painter

data class RecipeFolder(val name: String, val recipeCount: Int)

data class Recipe(val image: Painter?, val title: String)
