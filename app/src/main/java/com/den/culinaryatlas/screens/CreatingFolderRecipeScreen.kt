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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
fun CreatingFolderRecipeScreen(
    navController: NavController
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val photoUrl = painterResource(id = R.drawable.recipe_image)
    CreatingFolderRecipe(navController, montserratAlternatesItalicFont, photoUrl)
}

@Composable
fun CreatingFolderRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    photoUrl: Painter
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .size(26.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navController.popBackStack()
                },
            painter = painterResource(id = R.drawable.back_screen_icon),
            contentDescription = "Вернутся на предыдущий экран"
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Создание папки с рецептами",
                fontSize = 18.sp,
                fontFamily = montserratAlternatesItalicFont
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(
                        color = SoftOrange,
                        shape = RoundedCornerShape(16.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Добавить название папки",
                    fontSize = 14.sp,
                    fontFamily = montserratAlternatesItalicFont
                )

                SectionCreateFolder(montserratAlternatesItalicFont)

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Добавить рецепты",
                    fontSize = 14.sp,
                    fontFamily = montserratAlternatesItalicFont
                )
                AddRecipe(navController, montserratAlternatesItalicFont, photoUrl)

                AddRecipeSection(montserratAlternatesItalicFont, photoUrl)

                SaveButtonCreatingFolder(montserratAlternatesItalicFont)
            }

        }
    }
}

@Composable
fun AddRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    photoUrl: Painter
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(NavigationRoute.ViewRecipeScreen.route)
            }
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
        ) {
            if (photoUrl != null) {
                Image(
                    modifier = Modifier
                        .size(90.dp)
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = photoUrl,
                    contentDescription = "Фотография готового блюда"
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = R.drawable.recipe_image),
                    contentDescription = "Заменяющее изображение"
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically),
                text = "Пирог с вишней в духовке",
                fontSize = 14.sp,
                fontFamily = montserratAlternatesItalicFont,
            )
        }
    }
}

@Composable
fun SectionCreateFolder(
    montserratAlternatesItalicFont: FontFamily
) {
    var nameText by remember { mutableStateOf("") }
    var isBoxNameClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .height(48.dp)
            .clickable { isBoxNameClicked = true }
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        if (isBoxNameClicked) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp, top = 16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ),
                value = nameText,
                onValueChange = { nameText = it },
                textStyle = TextStyle(color = Color.Black),
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .align(Alignment.Center)
                    .height(32.dp)
                    .clickable { isBoxNameClicked = true }
                    .border(1.5.dp, Gray, RoundedCornerShape(6.dp))
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Добавить назание",
                    fontFamily = montserratAlternatesItalicFont,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun AddRecipeSection(
    montserratAlternatesItalicFont: FontFamily,
    photoUrl: Painter
) {
    var isBoxNameClicked by remember { mutableStateOf(false) }
    var isBoxAddRecipeClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .height(90.dp)
            .clickable { isBoxNameClicked = true }
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        if (isBoxAddRecipeClicked) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
            ) {
                if (photoUrl != null) {
                    Image(
                        modifier = Modifier
                            .size(90.dp)
                            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        painter = photoUrl,
                        contentDescription = "Фотография готового блюда"
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        painter = painterResource(id = R.drawable.recipe_image),
                        contentDescription = "Заменяющее изображение"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically),
                    text = "Пирог с вишней в духовке",
                    fontSize = 14.sp,
                    fontFamily = montserratAlternatesItalicFont,
                )
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.Center)
                    .height(70.dp)
                    .clickable { isBoxAddRecipeClicked = true }
                    .border(1.5.dp, Gray, RoundedCornerShape(12.dp))
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Добавить рецепт",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SaveButtonCreatingFolder(
    montserratAlternatesItalicFont: FontFamily
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 90.dp, end = 90.dp, bottom = 16.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = { }
        ) {
            Text(
                text = "Сохранить",
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = montserratAlternatesItalicFont
            )
        }
    }
}