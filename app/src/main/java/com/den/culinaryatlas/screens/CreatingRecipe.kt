package com.den.culinaryatlas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CreatingRecipe() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Text(text = "2")
    }
}