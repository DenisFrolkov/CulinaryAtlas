package com.den.culinaryatlas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.screens.CreatingFolderRecipeScreen
import com.den.culinaryatlas.screens.CreatingRecipeScreen
import com.den.culinaryatlas.screens.FolderRecipeScreen
import com.den.culinaryatlas.screens.MyRecipeScreen
import com.den.culinaryatlas.screens.TabRowScreen
import com.den.culinaryatlas.screens.ViewFolderScreen
import com.den.culinaryatlas.screens.ViewRecipeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoute.TabRowScreen.route) {
        composable(NavigationRoute.MyRecipeScreen.route) {
            MyRecipeScreen(navController)
        }
        composable(NavigationRoute.TabRowScreen.route) {
            TabRowScreen(navController)
        }
        composable(NavigationRoute.ViewRecipeScreen.route) {
            ViewRecipeScreen(navController)
        }
        composable(NavigationRoute.ViewFolderScreen.route) {
            ViewFolderScreen(navController)
        }
        composable(NavigationRoute.CreatingRecipeScreen.route) {
            CreatingRecipeScreen(navController)
        }
        composable(NavigationRoute.CreatingFolderRecipeScreen.route) {
            CreatingFolderRecipeScreen(navController)
        }
        composable(NavigationRoute.FolderRecipeScreen.route) {
            FolderRecipeScreen(navController)
        }
    }
}