package com.den.culinaryatlas.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.den.culinaryatlas.screens.CreatingRecipe
import com.den.culinaryatlas.screens.PreviewRecipeScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "preview_recipe_screen"){
        composable("preview_recipe_screen"){
            PreviewRecipeScreen()
        }
        composable("creating_recipe_screen"){
            CreatingRecipe()
        }
    }
}