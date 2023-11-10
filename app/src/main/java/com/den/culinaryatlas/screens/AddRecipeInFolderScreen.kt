package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.folder.Folder
import com.den.culinaryatlas.data.folder.FolderEvent
import com.den.culinaryatlas.data.folder.FolderState
import com.den.culinaryatlas.data.folder.FolderViewModel
import com.den.culinaryatlas.data.recipe.RecipeEvent
import com.den.culinaryatlas.data.recipe.RecipeState
import com.den.culinaryatlas.data.recipe.RecipeViewModel
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderEvent
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderState
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderViewModel
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun AddRecipeInFolderScreen(
    navController: NavController,
    stateRecipeState: RecipeState,
    onRecipeInFolderEvent: (RecipeInFolderEvent) -> Unit,
    viewFolderModel: FolderViewModel,
    folder: String
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))

    val folderState by produceState<Folder?>(initialValue = null) {
        val folderModel = viewFolderModel.getFolderById(folder)
        value = folderModel
    }

    folderState?.let {
        AddRecipeInFolder(
            navController,
            montserratAlternatesItalicFont,
            stateRecipeState,
            onRecipeInFolderEvent,
            folderState!!
        )
    }
}



@Composable
fun AddRecipeInFolder(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    stateRecipeState: RecipeState,
    onRecipeInFolderEvent: (RecipeInFolderEvent) -> Unit,
    folder: Folder
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoftOrange)
    ) {
        LazyColumn {
            onRecipeInFolderEvent(RecipeInFolderEvent.SetFolderId(folder.FolderId))
            item {
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
                    contentDescription = "Вернуться на предыдущий экран",
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    text = "Добавить рецепты в папку",
                    fontFamily = montserratAlternatesItalicFont,
                    fontSize = 18.sp,
                )
            }
            items(stateRecipeState.recipes) { recipeState ->
                var click by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            click = !click
                        }
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
                ) {
                    if (click) onRecipeInFolderEvent(RecipeInFolderEvent.SetRecipeId(recipeState.recipeId)) else onRecipeInFolderEvent(RecipeInFolderEvent.SetRecipeId(0))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
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
                            text = recipeState.title
                        )
                        if (click) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(imageVector = Icons.Default.Done, contentDescription = "в")
                            }
                        }
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 20.dp),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(start = 90.dp, end = 90.dp, bottom = 16.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        onClick = {
                            onRecipeInFolderEvent(RecipeInFolderEvent.SaveRecipeInFolder)
                        },
                    ) {
                        androidx.compose.material3.Text(
                            text = "Сохранить",
                            fontSize = 14.sp,
                            fontFamily = montserratAlternatesItalicFont,
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }
}
