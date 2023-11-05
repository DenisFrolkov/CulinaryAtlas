package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.DataRecipe
import com.den.culinaryatlas.data.Recipe
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun ViewFolderScreen(
    navController: NavController
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val data = DataRecipe()
    val items = listOf("Редактировать", "Удалить")
    ViewFolder(navController, montserratAlternatesItalicFont, items, data.recipes)
}

@Composable
fun ViewFolder(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    items: List<String>,
    recipes: List<Recipe>
){
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .size(26.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            navController.popBackStack()
                        },
                    painter = painterResource(id = R.drawable.back_screen_icon),
                    contentDescription = "Вернуться на предыдуший экран",
                )
                Box(
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        modifier = Modifier
                            .size(26.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                expanded = true
                            },
                        painter = painterResource(id = R.drawable.edit_infomation_item),
                        contentDescription = "Изменить информацию"
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .size(184.dp, 110.dp)
                            .background(Color.White, RoundedCornerShape(12.dp))
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    when (item) {
                                        "Редактировать" -> {
                                            // Действие при выборе "Редактировать"
                                        }

                                        "Удалить" -> {
                                            // Действие при выборе "Удалить"
                                        }
                                    }
                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item,
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        fontFamily = montserratAlternatesItalicFont
                                    )
                                }
                            }
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = "Папка 1",
                            fontSize = 18.sp,
                            fontFamily = montserratAlternatesItalicFont
                        )
                    }
                }
                this.items(recipes) { recipe ->
                    RecipeViewFolder(navController, montserratAlternatesItalicFont, recipe)
                }
            }
        }
    }
}

@Composable
fun RecipeViewFolder(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    recipe: Recipe
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(NavigationRoute.ViewRecipeScreen.route)
            }
            .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SoftOrange, shape = RoundedCornerShape(12.dp))
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