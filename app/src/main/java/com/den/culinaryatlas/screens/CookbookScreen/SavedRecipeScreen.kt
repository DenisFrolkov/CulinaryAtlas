package com.den.culinaryatlas.screens.CookbookScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.culinaryatlas.R

@Composable
fun SavedRecipeScreen() {
    val roundedFont = FontFamily(Font(R.font.brushscriptmtrusbyme_italic))
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp),
            text = "Сохраненные рецепты",
            fontFamily = roundedFont,
            fontSize = 28.sp
        )
    }
}