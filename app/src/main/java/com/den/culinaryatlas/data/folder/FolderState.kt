package com.den.culinaryatlas.data.folder

data class FolderState(
    val folders: List<Folder> = emptyList(),
    val folderId: Int = 0,
    val title: String = "",
    val recipeQuantity: String = "",
    val isAddingFolder: Boolean = false,
    val sortType: FolderSortType = FolderSortType.TITLE
)
