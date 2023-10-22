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
        startDestination = NavigationRoute.AuthorizationScreen.route
    ) {
        composable(NavigationRoute.AuthorizationScreen.route){
            AuthorizationScreen(navController)
        }
        composable(NavigationRoute.RegistrationScreen.route){
            RegistrationScreen(navController)
        }
        composable(NavigationRoute.BottomnavigationScreens.route){
            BottomNavigationScreen{
                navController.navigate(NavigationRoute.AuthorizationScreen.route){
                    popUpTo(NavigationRoute.AuthorizationScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }
}