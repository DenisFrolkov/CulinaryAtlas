package com.den.culinaryatlas.data.recipe_in_folder

data class RecipeInFolderState(
    val recipesInFolder: List<RecipeInFolder> = emptyList(),
    val recipeId: Int = 0,
    val folderId: Int = 0,
    val isAddingRecipeInFolder: Boolean = false,
    val recipeInFolderSortType: RecipeInFolderSortType = RecipeInFolderSortType.TITLE
)