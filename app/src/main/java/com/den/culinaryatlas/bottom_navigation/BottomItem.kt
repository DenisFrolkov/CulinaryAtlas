package com.den.culinaryatlas.bottom_navigation

import com.den.culinaryatlas.R

sealed class BottomItem(val title: String, val icon: Int, val route: String) {
    object RecipeScreen: BottomItem("preview_recipe_screen", R.drawable.recipe_icon, "preview_recipe_screen")
    object CreatingRecipeScreen: BottomItem("creating_recipe_screen", R.drawable.creating_recipe_icon, "creating_recipe_screen")
}
