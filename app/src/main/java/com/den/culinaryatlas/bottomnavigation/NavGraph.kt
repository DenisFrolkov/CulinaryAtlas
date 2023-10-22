package com.den.culinaryatlas.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.den.culinaryatlas.screens.CookbookScreen
import com.den.culinaryatlas.screens.ProfileScreen
import com.den.culinaryatlas.screens.SearchScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Route.SearchScreen.route){
        composable(Route.SearchScreen.route) {
            SearchScreen()
        }
        composable(Route.CookbookScreen.route) {
            CookbookScreen()
        }
        composable(Route.ProfileScreen.route) {
            ProfileScreen()
        }

    }
}

