package com.den.culinaryatlas.data.recipe

data class RecipeState(
    val recipes: List<Recipe> = emptyList(),
    val title: String = "",
    val ingredient: String = "",
    val action: String = "",
    val isAddingRecipe: Boolean = false,
    val sortType: RecipeSortType = RecipeSortType.TITLE
)
