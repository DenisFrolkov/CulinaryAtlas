package com.den.culinaryatlas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.Gray
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun FolderRecipeScreen(
    navController: NavController
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    FolderRecipe(navController, montserratAlternatesItalicFont)
}
@Composable
fun FolderRecipe(navController: NavController, montserratAlternatesItalicFont: FontFamily){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        CreatingFolderFolderRecipe(
            navController, montserratAlternatesItalicFont
        )
        LazyColumn {
                item {
                    Text(
                        text = "Созданные папки",
                        fontSize = 14.sp,
                        fontFamily = montserratAlternatesItalicFont
                    )
                }
            items(3) {item ->
                ItemFolderRecipe(navController, montserratAlternatesItalicFont)
            }
        }
    }
}

@Composable
fun CreatingFolderFolderRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(16.dp)
            .border(.1.dp, Color.Black, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navController.navigate(NavigationRoute.CreatingFolderRecipeScreen.route)
                }
                .border(1.5.dp, Gray, RoundedCornerShape(12.dp))
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(10.dp),
                text = "Создать папку",
                fontSize = 14.sp,
                fontFamily = montserratAlternatesItalicFont
            )
        }
    }
}

@Composable
fun ItemFolderRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(top = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(NavigationRoute.ViewFolderScreen.route)
            }
            .background(SoftOrange, RoundedCornerShape(12.dp))
            .border(.1.dp, Gray, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "f",
                fontSize = 18.sp,
                fontFamily = montserratAlternatesItalicFont
            )
            Text(
                text = "folder.recipeCount.toString()",
                fontSize = 18.sp,
                fontFamily = montserratAlternatesItalicFont
            )
        }
    }
}

