package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.den.culinaryatlas.data.Recipe
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.DataRecipe
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun MyRecipeScreen(navController: NavController) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val recipes = DataRecipe()

    MyRecipe(navController, montserratAlternatesItalicFont, recipes.recipes)
}

@Composable
fun MyRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    recipes: List<Recipe>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 10.dp),
                text = "Созданные рецепты",
                fontSize = 14.sp,
                fontFamily = montserratAlternatesItalicFont
            )
        }
        this.items(recipes) { recipe ->
            Recipe(navController, montserratAlternatesItalicFont, recipe)
        }
    }
}

@Composable
fun Recipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    recipe: Recipe
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(NavigationRoute.ViewRecipeScreen.route)
            }
            .padding(bottom = 16.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SoftOrange)
                .padding(16.dp)
        ) {
            if (recipe.image != null) {
                Image(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = recipe.image,
                    contentDescription = "Фото готового блюда"
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = R.drawable.photo_space_image),
                    contentDescription = "Заменяющее изображение"
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp,
                fontFamily = montserratAlternatesItalicFont,
                text = recipe.title
            )
        }
    }
}
