package com.den.culinaryatlas.data.folder

import com.den.culinaryatlas.data.recipe.Recipe

data class FolderState(
    val folders: List<Folder> = emptyList(),
    val folderId: Int = 0,
    val title: String = "",
    val recipeQuantity: String = "",
    val sortType: FolderSortType = FolderSortType.TITLE
)
