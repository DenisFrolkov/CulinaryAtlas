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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.folder.Folder
import com.den.culinaryatlas.data.folder.FolderEvent
import com.den.culinaryatlas.data.folder.FolderViewModel
import com.den.culinaryatlas.data.recipe.Recipe
import com.den.culinaryatlas.data.recipe.RecipeState
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderEvent
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderState
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.BasicOrange
import com.den.culinaryatlas.ui.theme.Gray
import com.den.culinaryatlas.ui.theme.SoftOrange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ViewFolderScreen(
    navController: NavController,
    viewFolderModel: FolderViewModel,
    folderId: String,
    stateRecipeInFolder: RecipeInFolderState,
    onFolderEvent: (FolderEvent) -> Unit,
    stateRecipeState: RecipeState,
    onRecipeInFolderEvent: (RecipeInFolderEvent) -> Unit
) {
    val folder by produceState<Folder?>(initialValue = null) {
        value = viewFolderModel.getFolderById(folderId)
    }

    val recipesInFolder by viewFolderModel.getRecipesInFolder(folderId).collectAsState(emptyList())

    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val items = listOf("Редактировать", "Удалить")

    folder?.let {
        ViewFolder(
            navController,
            montserratAlternatesItalicFont,
            items,
            it,
            onFolderEvent,
            viewFolderModel,
            stateRecipeState,
            recipesInFolder,
            onRecipeInFolderEvent
        )
    }
}


@Composable
fun ViewFolder(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    items: List<String>,
    folder: Folder,
    onFolderEvent: (FolderEvent) -> Unit,
    viewFolderModel: FolderViewModel,
    stateRecipeState: RecipeState,
    recipesInFolder: List<Recipe>,
    onRecipeInFolderEvent: (RecipeInFolderEvent) -> Unit

) {
    var showDialog by remember { mutableStateOf(false) }
    val myCoroutineScope = CoroutineScope(Dispatchers.IO)
    var shouldClosePage by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
                                .width(IntrinsicSize.Max)
                                .size(184.dp, 110.dp)
                                .background(Color.White, RoundedCornerShape(12.dp))
                        ) {
                            items.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    onClick = {
                                        expanded = false
                                        when (index) {
                                            0 -> {
                                                showDialog = true
                                            }

                                            1 -> {
                                                myCoroutineScope.launch {
                                                    onFolderEvent(FolderEvent.DeleteFolder(folder))
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(top = 16.dp),
                                text = folder.title,
                                fontSize = 18.sp,
                                fontFamily = montserratAlternatesItalicFont
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, top = 10.dp, bottom = 10.dp, end = 30.dp)
                                .height(40.dp)
                                .clickable { navController.navigate(NavigationRoute.AddRecipeInFolderScreen.route + "/${folder.FolderId}") }
                                .border(1.5.dp, Gray, RoundedCornerShape(12.dp))
                        ) {
                            androidx.compose.material3.Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = "Добавить рецепт",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    items(recipesInFolder) { recipeItem ->
                        RecipeViewFolder(
                            navController,
                            montserratAlternatesItalicFont,
                            recipeItem,
                            onRecipeInFolderEvent,
                            myCoroutineScope,
                            folder,
                            viewFolderModel
                        )
                    }
                }
            }
            if (showDialog) {
                EditFolderDialog(
                    onDismiss = {
                        showDialog = false
                        shouldClosePage = showDialog
                    },
                    folder,
                    viewFolderModel,
                    onFolderEvent,
                    montserratAlternatesItalicFont
                )
            }
        } else {
            navController.navigate(NavigationRoute.TabRowScreen.route)
        }
    }
}

@Composable
fun EditFolderDialog(
    onDismiss: () -> Unit,
    folder: Folder,
    FolderViewModel: FolderViewModel,
    onFolderEvent: (FolderEvent) -> Unit,
    montserratAlternatesItalicFont: FontFamily
) {

    val myCoroutineScope = CoroutineScope(Dispatchers.IO)

    var title by remember { mutableStateOf(folder.title) }

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
            Button(
                onClick = {
                    val updatedFolder = Folder(
                        title = title,
                        FolderId = folder.FolderId
                    )
                    myCoroutineScope.launch {
                        onFolderEvent(FolderEvent.UpdateFolder(updatedFolder))
                        onDismiss()
                    }
                },
                colors = ButtonDefaults.buttonColors(BasicOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(BasicOrange)
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
fun RecipeViewFolder(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    recipeItem: Recipe,
    onRecipeInFolderEvent: (RecipeInFolderEvent) -> Unit,
    myCoroutineScope: CoroutineScope,
    folder: Folder,
    viewFolderModel: FolderViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(NavigationRoute.ViewRecipeScreen.route + "/${recipeItem.recipeId}")
            }
            .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SoftOrange, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = R.drawable.photo_space_image),
                contentDescription = "Заменяющее изображение"
            )

            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp,
                fontFamily = montserratAlternatesItalicFont,
                text = recipeItem.title
            )
        }
    }
}