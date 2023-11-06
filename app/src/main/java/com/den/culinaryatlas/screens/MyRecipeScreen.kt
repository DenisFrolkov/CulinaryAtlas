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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.Recipe
import com.den.culinaryatlas.data.RecipeEvent
import com.den.culinaryatlas.data.RecipeState
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun MyRecipeScreen(
    navController: NavController,
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val imageURL: Painter = painterResource(id = R.drawable.photo_space_image)
    if (state.isAddingRecipe) {
        CreatingRecipeScreen(navController, state = state, onEvent = onEvent)
    }

    MyRecipe(navController, montserratAlternatesItalicFont, imageURL, state, onEvent)
}

@Composable
fun MyRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    imageURL: Painter,
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
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

        items(state.recipes) { recipeItem ->
            Recipe(navController, montserratAlternatesItalicFont, onEvent, imageURL, recipeItem, state)
        }
    }
}

@Composable
fun Recipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    onEvent: (RecipeEvent) -> Unit,
    imageURL: Painter,
    recipeItem: Recipe,
    state: RecipeState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(NavigationRoute.ViewRecipeScreen.route + recipeItem) {
                        launchSingleTop = true
                        restoreState = true
                        val args = bundleOf("recipeItem" to recipeItem)
                    }
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
            if (imageURL != null) {
                Image(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = R.drawable.recipe_image),
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
                text = recipeItem.title
            )
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        onEvent(RecipeEvent.DeleteRecipe(recipeItem))
                    }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "в")
            }
        }
    }
}
