package com.den.culinaryatlas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.data.RecipeEvent
import com.den.culinaryatlas.data.RecipeState
import com.den.culinaryatlas.screens.CreatingFolderRecipeScreen
import com.den.culinaryatlas.screens.CreatingRecipeScreen
import com.den.culinaryatlas.screens.TabRowScreen
import com.den.culinaryatlas.screens.ViewFolderScreen
import com.den.culinaryatlas.screens.ViewRecipeScreen

@Composable
fun Navigation(
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit,
) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavigationRoute.TabRowScreen.route) {
        composable(NavigationRoute.TabRowScreen.route) {
            TabRowScreen(navController, state, onEvent)
        }
        composable(NavigationRoute.ViewRecipeScreen.route) {
            ViewRecipeScreen(navController)
        }
        composable(NavigationRoute.ViewFolderScreen.route) {
            ViewFolderScreen(navController)
        }
        composable(NavigationRoute.CreatingRecipeScreen.route) {
            CreatingRecipeScreen(navController, state, onEvent)
        }
        composable(NavigationRoute.CreatingFolderRecipeScreen.route) {
            CreatingFolderRecipeScreen(navController)
        }
    }
}