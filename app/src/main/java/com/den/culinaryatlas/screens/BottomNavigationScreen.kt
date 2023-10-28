package com.den.culinaryatlas.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.bottom_navigation.BottomNavigation
import com.den.culinaryatlas.bottom_navigation.NavGraph

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
        BottomNavigation(navController = navController)
    }
    ) {
        NavGraph(navHostController = navController)
    }
}