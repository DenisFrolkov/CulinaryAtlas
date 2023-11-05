package com.den.culinaryatlas.navigation

sealed class NavigationRoute(val route: String) {
    object CreatingFolderRecipeScreen : NavigationRoute("creating_folder_recipe_screen")
    object CreatingRecipeScreen : NavigationRoute("creating_recipe_screen")
    object TabRowScreen : NavigationRoute("tab_row_screen")
    object ViewFolderScreen : NavigationRoute("view_folder_screen")
    object ViewRecipeScreen : NavigationRoute("view_recipe_screen")
}