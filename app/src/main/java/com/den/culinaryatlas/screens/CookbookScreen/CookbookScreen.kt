package com.den.culinaryatlas.screens.CookbookScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.cookbooktabitem.CookbookTabRow

@Composable
fun CookbookScreen() {
    val navController = rememberNavController()
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        CookbookTabRow()
    }
}