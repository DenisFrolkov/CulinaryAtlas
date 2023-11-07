package com.den.culinaryatlas.data.folder

data class FolderState(
    val folders: List<Folder> = emptyList(),
    val title: String = "",
    val recipeQuantity: String = "",
    val isAddingFolder: Boolean = false,
    val sortType: FolderSortType = FolderSortType.TITLE
)
