package com.den.culinaryatlas.screens.CookbookScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.culinaryatlas.R
import com.den.culinaryatlas.ui.theme.Orange1

@Preview
@Composable
fun MyRecipeScreen(
) {
    val roundedFont = FontFamily(Font(R.font.brushscriptmtrusbyme_italic))
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp),
            contentAlignment = Alignment.BottomEnd
        ){
            FloatingActionButton(
                containerColor = Orange1,
                shape = RoundedCornerShape(50.dp),
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp),
            text = "Мои рецепты",
            fontFamily = roundedFont,
            fontSize = 28.sp
        )
    }
}