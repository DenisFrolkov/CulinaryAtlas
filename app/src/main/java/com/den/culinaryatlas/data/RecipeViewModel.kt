package com.den.culinaryatlas.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeViewModel(private val recipeDao: RecipeDao) : ViewModel() {
    private val _sortType = MutableStateFlow(SortType.TITLE)
    private val _recipes = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.TITLE -> recipeDao.getRecipesOrderedByLastTitle()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(RecipeState())
    val state = combine(_state, _sortType, _recipes) { state, sortType, recipes ->
        state.copy(
            recipes = recipes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecipeState())

    fun onEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.DeleteRecipe -> {
                viewModelScope.launch {
                    recipeDao.deleteRecipe(event.recipe)
                }
            }

            RecipeEvent.SaveRecipe -> {
                val title = state.value.title
                val ingredient = state.value.ingredient
                val action = state.value.action

                if (title.isBlank()) return

                val recipe = Recipe(
                    title = title,
                    ingredient = ingredient,
                    action = action,
                )
                viewModelScope.launch {
                    recipeDao.upsertRecipe(recipe)
                }
                _state.update {
                    it.copy(
                        isAddingRecipe = false,
                        title = "",
                        ingredient = "",
                        action = ""
                    )
                }
            }

            is RecipeEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }

            is RecipeEvent.SetIngredient -> {
                _state.update {
                    it.copy(
                        ingredient = event.ingredient
                    )
                }
            }

            is RecipeEvent.SetAction -> {
                _state.update {
                    it.copy(
                        action = event.action
                    )
                }
            }

            is RecipeEvent.SortRecipes -> {
                _sortType.value = event.sortType
            }

        }
    }
}

