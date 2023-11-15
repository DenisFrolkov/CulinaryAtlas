package com.den.culinaryatlas.data.recipe_in_folder

sealed class RecipeInFolderEvent {
    object SaveRecipeInFolder: RecipeInFolderEvent()
    data class SetFolderId(val folderId: Int): RecipeInFolderEvent()
    data class SetRecipeId(val recipeId: Int): RecipeInFolderEvent()
    data class SortRecipeInFolder(val recipeInFolderSortType: RecipeInFolderSortType): RecipeInFolderEvent()
    data class DeleteRecipeInFolder(val recipeInFolder: RecipeInFolder): RecipeInFolderEvent()
}