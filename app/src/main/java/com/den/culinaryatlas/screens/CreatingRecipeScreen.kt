package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.recipe.RecipeEvent
import com.den.culinaryatlas.data.recipe.RecipeState
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.Gray
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun CreatingRecipeScreen(
    navController: NavController,
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val keyboardController = LocalSoftwareKeyboardController.current

    CreatingRecipe(
        navController,
        montserratAlternatesItalicFont,
        onEvent,
        state,
        keyboardController
    )
}

@Composable
fun CreatingRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    onEvent: (RecipeEvent) -> Unit,
    state: RecipeState,
    keyboardController: SoftwareKeyboardController?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                keyboardController?.hide()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
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
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 16.dp)
                .background(
                    color = SoftOrange,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                navController.navigate(NavigationRoute.ViewRecipeScreen.route)
                            },
                        text = "Создать рецепт",
                        fontSize = 14.sp,
                        fontFamily = montserratAlternatesItalicFont
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Добавить фото готового рецепта",
                        fontSize = 14.sp,
                        fontFamily = montserratAlternatesItalicFont
                    )

                    ImageCreatingRecipe()

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Добавить название рецепта",
                        fontSize = 14.sp,
                        fontFamily = montserratAlternatesItalicFont
                    )

                    AddTitleCreatingRecipe(montserratAlternatesItalicFont, state, onEvent)

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Добавить нужный ингридиент",
                        fontSize = 14.sp,
                        fontFamily = montserratAlternatesItalicFont
                    )

                    AddIngredientCreatingRecipe(montserratAlternatesItalicFont, state, onEvent)

                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = "Добавить описание для действий",
                        fontSize = 14.sp,
                        fontFamily = montserratAlternatesItalicFont
                    )

                    AddActionCreatingRecipe( montserratAlternatesItalicFont, state, onEvent )

                    SaveButtonCreatingRecipe(montserratAlternatesItalicFont, onEvent)

                }
            }
        }
    }
}

@Composable
fun ImageCreatingRecipe() {
    Box(modifier = Modifier.padding(top = 10.dp)) {
        Image(
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = R.drawable.photo_space_image),
            contentDescription = "Заменяющее изображение"
        )
    }
}

@Composable
fun AddTitleCreatingRecipe(
    montserratAlternatesItalicFont: FontFamily,
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterStart)
                .padding(start = 16.dp, top = 16.dp)
                .background(color = Color.White),
            value = state.title,
            onValueChange = {
                onEvent(RecipeEvent.SetTitle(it))
            },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontFamily = montserratAlternatesItalicFont),
        )
    }
}

@Composable
fun AddIngredientCreatingRecipe(
    montserratAlternatesItalicFont: FontFamily,
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterStart)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp)
                .background(color = Color.White),
            value = state.ingredient,
            onValueChange = {
                onEvent(RecipeEvent.SetIngredient(it))
            },
            textStyle = TextStyle(color = Color.Black, lineHeight = 20.sp, fontFamily = montserratAlternatesItalicFont)
        )
    }
}

@Composable
fun AddActionCreatingRecipe(
    montserratAlternatesItalicFont: FontFamily,
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterStart)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp)
                .background(color = Color.White),
            value = state.action,
            onValueChange = {
                onEvent(RecipeEvent.SetAction(it))
            },
            textStyle = TextStyle(color = Color.Black, lineHeight = 20.sp, fontFamily = montserratAlternatesItalicFont),
        )
    }
}

@Composable
fun SaveButtonCreatingRecipe(
    montserratAlternatesItalicFont: FontFamily,
    onEvent: (RecipeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 90.dp, end = 90.dp, bottom = 16.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = {
                onEvent(RecipeEvent.SaveRecipe)
            }
        ) {
            androidx.compose.material3.Text(
                text = "Сохранить",
                fontSize = 14.sp,
                fontFamily = montserratAlternatesItalicFont,
                color = Color.Black
            )
        }
    }
}

