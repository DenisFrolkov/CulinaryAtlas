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
        startDestination = "authorization"
    ) {
        composable("authorization"){
            AuthorizationScreen {
                navController.navigate("registration")
            }
        }
        composable("registration"){
            RegistrationScreen {
                navController.navigate("bottomnavigation")
            }
        }
        composable("bottomnavigation"){
            BottomNavigationScreen{
                navController.navigate("authorization"){
                    popUpTo("authorization"){
                        inclusive = true
                    }
                }
            }
        }

    }
}