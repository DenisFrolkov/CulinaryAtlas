package com.den.culinaryatlas.data.recipe_in_folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeInFolderViewModel( private val recipeInFolderDao: RecipeInFolderDao ) : ViewModel() {
    private val _sortType = MutableStateFlow(RecipeInFolderSortType.TITLE)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _recipeInFolder = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                RecipeInFolderSortType.TITLE -> recipeInFolderDao.getRecipeInFolderByLastFolderId()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(RecipeInFolderState())
    val recipeInFolderState = combine(_state, _sortType, _recipeInFolder) {recipeInFolderState, recipeInFolderSortType, recipesInFolder ->
        recipeInFolderState.copy(
            recipeInFolderSortType = recipeInFolderSortType,
            recipesInFolder = recipesInFolder
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecipeInFolderState())

    suspend fun getRecipesInFolder(folderId: String): List<RecipeInFolder> {
        return withContext(Dispatchers.IO) {
            recipeInFolderDao.getRecipesInFolder(folderId)
        }
    }

    fun onRecipeInFolderEvent(event: RecipeInFolderEvent) {
        when (event) {
            is RecipeInFolderEvent.DeleteRecipeInFolder -> {
                viewModelScope.launch {
                    recipeInFolderDao.delete(event.recipeInFolder)
                }
            }

            RecipeInFolderEvent.SaveRecipeInFolder -> {
                val folderId = recipeInFolderState.value.folderId
                val recipeId = recipeInFolderState.value.recipeId

                if (folderId == 0 || recipeId == 0) return

                _state.update {
                    it.copy(
                        isAddingRecipeInFolder = false,
                        folderId = 0,
                        recipeId = 0
                    )
                }

                val recipeInFolder = RecipeInFolder(
                    folderId = folderId,
                    recipeId = recipeId
                )

                viewModelScope.launch {
                    recipeInFolderDao.upsert(recipeInFolder)
                }
            }


            is RecipeInFolderEvent.SetFolderId -> {
                _state.update{
                    it.copy(
                        folderId = event.folderId
                    )
                }
            }

            is RecipeInFolderEvent.SetRecipeId -> {
                _state.update {
                    it.copy(
                        recipeId = event.recipeId
                    )
                }
            }

            is RecipeInFolderEvent.SortRecipeInFolder -> {
                _sortType.value = event.recipeInFolderSortType
            }
        }
    }
}

