package com.den.culinaryatlas.navigatingscreens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.bottomnavigation.BottomNavigationScreen
import com.den.culinaryatlas.screens.AuthorizationScreen
import com.den.culinaryatlas.screens.RegistrationScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.AuthorizationScreen.route
    ) {
        composable(Route.AuthorizationScreen.route){
            AuthorizationScreen(navController)
        }
        composable(Route.RegistrationScreen.route){
            RegistrationScreen(navController)
        }
        composable(Route.BottomnavigationScreens.route){
            BottomNavigationScreen{
                navController.navigate(Route.AuthorizationScreen.route){
                    popUpTo(Route.AuthorizationScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }
}