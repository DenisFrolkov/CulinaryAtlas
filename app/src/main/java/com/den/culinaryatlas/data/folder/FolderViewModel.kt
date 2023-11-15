package com.den.culinaryatlas.data.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.culinaryatlas.data.recipe.Recipe
import com.den.culinaryatlas.data.recipe.RecipeDao
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
class FolderViewModel(private val folderDao: FolderDao, private val recipeDao: RecipeDao, private val recipeInFolderDao: RecipeInFolderDao) : ViewModel() {
    private val _sortType = MutableStateFlow(FolderSortType.TITLE)
    private val _folders = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                FolderSortType.TITLE -> folderDao.getFoldersOrderedByLastTitle()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(FolderState())
    val state = combine(_state, _sortType, _folders) {state, sortType, folders ->
        state.copy(
            folders = folders,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FolderState())

    suspend fun getFolderById(folderId: String): Folder {
        return withContext(Dispatchers.IO) {
            folderDao.getFolderById(folderId)
        }
    }

    fun getRecipeCountInFolder(folderId: Int): Flow<Int> {
        return recipeDao.getRecipeCountInFolder(folderId)
    }

    fun getRecipesInFolder(folderId: String): Flow<List<Recipe>> {
        return recipeDao.getRecipesInFolder(folderId)
    }


    suspend fun updateFolder(folder: Folder) {
        withContext(Dispatchers.IO) {
            folderDao.updateFolder(folder)
        }
    }

    fun onFolderEvent(event: FolderEvent) {
        when (event) {
            is FolderEvent.DeleteFolder -> {
                viewModelScope.launch {
                    folderDao.deleteFolder(event.folder)
                }
            }
            FolderEvent.SaveFolder -> {
                val title = state.value.title

                if (title.isBlank()) return

                val folder = Folder(
                    title = title
                )
                viewModelScope.launch {
                    folderDao.upsertFolder(folder)
                }
                _state.update {
                    it.copy(
                        title = "",
                        recipeQuantity = ""
                    )
                }
            }
            is FolderEvent.SetRecipeQuantity -> {
                _state.update {
                    it.copy(
                        recipeQuantity = event.recipeQuantity
                    )
                }
            }
            is FolderEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
            is FolderEvent.SortFolders -> {
                _sortType.value = event.folderSortType
            }

            is FolderEvent.UpdateFolder -> {
                val updateFolder = event.updatedFolder
                viewModelScope.launch {
                    updateFolder(updateFolder)
                }
            }
        }
    }
}