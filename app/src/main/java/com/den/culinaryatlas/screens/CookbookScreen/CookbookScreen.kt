package com.den.culinaryatlas.screens.CookbookScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.den.culinaryatlas.cookbooktabitem.CookbookTabRow

@Composable
fun CookbookScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 76.dp)
    ) {
        CookbookTabRow()
    }
}