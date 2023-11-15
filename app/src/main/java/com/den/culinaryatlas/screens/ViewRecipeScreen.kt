package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.recipe.Recipe
import com.den.culinaryatlas.data.recipe.RecipeEvent
import com.den.culinaryatlas.data.recipe.RecipeViewModel
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.BasicOrange
import com.den.culinaryatlas.ui.theme.SoftOrange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ViewRecipeScreen(
    navController: NavController,
    viewModel: RecipeViewModel,
    onRecipeEvent: (RecipeEvent) -> Unit,
    recipeId: String
) {
    val recipe by produceState<Recipe?>(initialValue = null) {
        val recipe = viewModel.getRecipeById(recipeId)
        value = recipe
    }

    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val photoUrl = painterResource(id = R.drawable.recipe_image)
    val items = listOf("Редактировать", "Удалить")

    recipe?.let {
        ViewRecipe(
            navController,
            montserratAlternatesItalicFont,
            photoUrl,
            items,
            onRecipeEvent,
            it,
            viewModel
        )
    }
}


@Composable
fun ViewRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    photoUrl: Painter,
    items: List<String>,
    onRecipeEvent: (RecipeEvent) -> Unit,
    recipe: Recipe,
    viewModel: RecipeViewModel
) {
    val myCoroutineScope = CoroutineScope(Dispatchers.IO)
    var shouldClosePage by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftOrange)
    ) {
        if (shouldClosePage) {
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
                                indication = null
                            ) {
                                navController.popBackStack()
                            },
                        painter = painterResource(id = R.drawable.back_screen_icon),
                        contentDescription = "Вернуться напредыдуший экрна"
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
                            modifier = Modifier.width(IntrinsicSize.Max),
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            items.forEachIndexed { index, item ->
                                androidx.compose.material.DropdownMenuItem(
                                    onClick = {
                                        expanded = false
                                        when (index) {
                                            0 -> {
                                                showDialog = true
                                            }

                                            1 -> {
                                                myCoroutineScope.launch {
                                                    onRecipeEvent(RecipeEvent.DeleteRecipe(recipe))
                                                    withContext(Dispatchers.Main) {
                                                        shouldClosePage = false
                                                    }
                                                }
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
                RecipeAction(montserratAlternatesItalicFont, photoUrl, recipe)
                if (showDialog) {
                    EditRecipeDialog(
                        onDismiss = {
                            showDialog = false
                            shouldClosePage = showDialog
                        },
                        recipe = recipe,
                        viewModel,
                        onRecipeEvent,
                        montserratAlternatesItalicFont
                    )
                }
            }
        } else {
            navController.navigate(NavigationRoute.TabRowScreen.route)
        }
    }
}

@Composable
fun EditRecipeDialog(
    onDismiss: () -> Unit,
    recipe: Recipe,
    RecipeViewModel: RecipeViewModel,
    onRecipeEvent: (RecipeEvent) -> Unit,
    montserratAlternatesItalicFont: FontFamily
) {

    val myCoroutineScope = CoroutineScope(Dispatchers.IO)

    var title by remember { mutableStateOf(recipe.title) }
    var ingredient by remember { mutableStateOf(recipe.ingredient) }
    var action by remember { mutableStateOf(recipe.action) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text("Введите новое название") },
                textStyle = TextStyle(fontFamily = montserratAlternatesItalicFont, fontSize = 16.sp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = ingredient,
                onValueChange = { ingredient = it },
                label = { Text("Добавьте новые ингредиенты") },
                textStyle = TextStyle(fontFamily = montserratAlternatesItalicFont, fontSize = 16.sp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = action,
                onValueChange = { action = it },
                label = { Text("Опишите новые действия") },
                textStyle = TextStyle(fontFamily = montserratAlternatesItalicFont, fontSize = 16.sp)
            )

            Button(
                onClick = {
                    val updatedRecipe = Recipe(
                        title = title,
                        ingredient = ingredient,
                        action = action,
                        recipeId = recipe.recipeId
                    )
                    myCoroutineScope.launch {
                        onRecipeEvent(RecipeEvent.UpdateRecipe(updatedRecipe))
                        onDismiss()
                    }
                },
                colors = ButtonDefaults.buttonColors(BasicOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .padding(8.dp)
            ) {
                Text(
                    "Сохранить",
                    fontFamily = montserratAlternatesItalicFont,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Composable
fun RecipeAction(
    montserratAlternatesItalicFont: FontFamily,
    photoUrl: Painter,
    recipe: Recipe
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = recipe.title,
            fontSize = 18.sp,
            fontFamily = montserratAlternatesItalicFont
        )
    }
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Image(
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = R.drawable.photo_space_image),
            contentDescription = "Заменяющее изображение"
        )
    }
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = "Ингредиенты",
        fontSize = 18.sp,
        fontFamily = montserratAlternatesItalicFont
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .border(.3.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            text = recipe.ingredient,
            fontSize = 14.sp,
            fontFamily = montserratAlternatesItalicFont,
            maxLines = Int.MAX_VALUE,
            overflow = TextOverflow.Clip
        )
    }
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = "Действия",
        fontSize = 18.sp,
        fontFamily = montserratAlternatesItalicFont
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .border(.3.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)
                .fillMaxWidth(),
            text = recipe.action,
            fontSize = 14.sp,
            fontFamily = montserratAlternatesItalicFont,
            maxLines = Int.MAX_VALUE,
            overflow = TextOverflow.Clip
        )
    }
}
