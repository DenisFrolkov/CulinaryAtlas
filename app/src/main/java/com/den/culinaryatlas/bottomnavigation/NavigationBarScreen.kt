package com.den.culinaryatlas.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationBarScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController = navController) },
        modifier = Modifier.fillMaxWidth()
    ) {
        NavGraph(navHostController = navController)
    }
}