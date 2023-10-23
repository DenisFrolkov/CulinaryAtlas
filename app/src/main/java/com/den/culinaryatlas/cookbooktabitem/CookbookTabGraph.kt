package com.den.culinaryatlas.cookbooktabitem

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.den.culinaryatlas.screens.CookbookScreen.MyRecipeScreen
import com.den.culinaryatlas.screens.CookbookScreen.SavedRecipeScreen

@Composable
fun CookbookTabGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = CookbookTabRoute.MyRecipeScreen.route
    ) {
        composable(CookbookTabRoute.MyRecipeScreen.route) {
            MyRecipeScreen()
        }
        composable(CookbookTabRoute.SavedRecipeScreen.route) {
            SavedRecipeScreen()
        }
    }
}