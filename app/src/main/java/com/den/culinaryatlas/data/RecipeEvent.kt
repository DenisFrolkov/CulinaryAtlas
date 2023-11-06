package com.den.culinaryatlas.data

sealed class RecipeEvent {
    object SaveRecipe: RecipeEvent()
    data class SetTitle(val title: String): RecipeEvent()
    data class SetIngredient(val ingredient: String): RecipeEvent()
    data class SetAction(val action: String): RecipeEvent()
    data class SortRecipes(val sortType: SortType): RecipeEvent()
    data class DeleteRecipe(val recipe: Recipe): RecipeEvent()

}
