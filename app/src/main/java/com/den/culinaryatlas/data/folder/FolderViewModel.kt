package com.den.culinaryatlas.data.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
class FolderViewModel(private val folderDao: FolderDao) : ViewModel() {
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

    fun onFolderEvent(event: FolderEvent) {
        when (event) {
            is FolderEvent.DeleteFolder -> {
                viewModelScope.launch {
                    folderDao.deleteFolder(event.folder)
                }
            }
            FolderEvent.SaveFolder -> {
                val title = state.value.title
                val recipeQuantity = state.value.recipeQuantity

                if (title.isBlank()) return

                val folder = Folder(
                    title = title,
                    recipeQuantity = recipeQuantity
                )
                viewModelScope.launch {
                    folderDao.upsertFolder(folder)
                }
                _state.update {
                    it.copy(
                        isAddingFolder = false,
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
        }
    }
}