package com.den.culinaryatlas.data.recipe

data class RecipeState(
    val recipes: List<Recipe> = emptyList(),
    val recipeId: Int = 0,
    val title: String = "",
    val ingredient: String = "",
    val action: String = "",
    val recipesUpdated: Boolean = false,
    val sortType: RecipeSortType = RecipeSortType.TITLE
)
