package com.den.culinaryatlas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.screens.CreatingFolderRecipeScreen
import com.den.culinaryatlas.screens.CreatingRecipeScreen
import com.den.culinaryatlas.screens.MyRecipeScreen
import com.den.culinaryatlas.screens.TabRowScreen
import com.den.culinaryatlas.screens.ViewFolderScreen
import com.den.culinaryatlas.screens.ViewRecipeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "tab_row_screen") {
        composable("tab_row_screen") {
            TabRowScreen(navController)
        }
        composable("view_recipe_screen") {
            ViewRecipeScreen(navController)
        }
        composable("my_recipe_screen") {
            MyRecipeScreen(navController)
        }
        composable("creating_recipe_screen") {
            CreatingRecipeScreen(navController)
        }
        composable("creating_folder_recipe_screen") {
            CreatingFolderRecipeScreen(navController)
        }
        composable("view_folder_screen") {
            ViewFolderScreen(navController)
        }
    }
}