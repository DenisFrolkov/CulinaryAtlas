package com.den.culinaryatlas.cookbooktabitem

sealed class CookbookTabRoute(val route: String) {
    object MyRecipeScreen : CookbookTabRoute("my_recipe_screen")
    object SavedRecipeScreen : CookbookTabRoute("cookbook_screen")
}