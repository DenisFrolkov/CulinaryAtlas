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
import com.den.culinaryatlas.data.folder.FolderEvent
import com.den.culinaryatlas.data.folder.FolderState
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.ui.theme.Gray
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun CreatingFolderRecipeScreen(
    navController: NavController,
    state: FolderState,
    onEvent: (FolderEvent) -> Unit
) {
    val montserratAlternatesItalicFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    CreatingFolderRecipe(navController, montserratAlternatesItalicFont, onEvent, state)
}

@Composable
fun CreatingFolderRecipe(
    navController: NavController,
    montserratAlternatesItalicFont: FontFamily,
    onEvent: (FolderEvent) -> Unit,
    state: FolderState
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                keyboardController?.hide()
            }
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
                    .fillMaxWidth()
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

                SectionCreateFolder(onEvent, state, keyboardController)

                SaveButtonCreatingFolder(montserratAlternatesItalicFont, onEvent)
            }
        }
    }
}

@Composable
fun SectionCreateFolder(
    onEvent: (FolderEvent) -> Unit,
    state: FolderState,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .height(48.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                keyboardController?.hide()
            }
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
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
            value = state.title,
            onValueChange = {
                onEvent(FolderEvent.SetTitle(it))
            },
            textStyle = TextStyle(color = Color.Black),
        )
    }
}


@Composable
fun SaveButtonCreatingFolder(
    montserratAlternatesItalicFont: FontFamily,
    onEvent: (FolderEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 90.dp, top = 16.dp, end = 90.dp, bottom = 16.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = { onEvent(FolderEvent.SaveFolder) }
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