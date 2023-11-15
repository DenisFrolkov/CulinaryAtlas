package com.den.culinaryatlas.data.folder

import com.den.culinaryatlas.data.recipe.Recipe
import com.den.culinaryatlas.data.recipe.RecipeEvent

sealed class FolderEvent {
    object SaveFolder: FolderEvent()
    data class SetTitle(val title: String): FolderEvent()
    data class SetRecipeQuantity(val recipeQuantity: String): FolderEvent()
    data class SortFolders(val folderSortType: FolderSortType): FolderEvent()
    data class UpdateFolder(val updatedFolder: Folder): FolderEvent()

    data class DeleteFolder(val folder: Folder): FolderEvent()
}