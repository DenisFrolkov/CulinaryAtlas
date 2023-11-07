package com.den.culinaryatlas.data.folder

sealed class FolderEvent {
    object SaveFolder: FolderEvent()
    data class SetTitle(val title: String): FolderEvent()
    data class SetRecipeQuantity(val recipeQuantity: String): FolderEvent()
    data class SortFolders(val folderSortType: FolderSortType): FolderEvent()
    data class DeleteFolder(val folder: Folder): FolderEvent()
}