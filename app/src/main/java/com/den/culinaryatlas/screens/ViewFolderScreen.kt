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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.den.culinaryatlas.ui.theme.SoftOrange

@Composable
fun ViewFolderScreen(
    navController: NavController
) {
    val montserrat_alternates_italic_font = FontFamily(Font(R.font.montserrat_alternates_italic))
    val photoUrl = painterResource(id = R.drawable.recipe_image)
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
                            indication = null
                        ) {
                            navController.popBackStack()
                        },
                    painter = painterResource(id = R.drawable.back_screen_icon),
                    contentDescription = "Вернуться напредыдуший экрна"
                )
                Icon(
                    modifier = Modifier
                        .size(26.dp),
                    painter = painterResource(id = R.drawable.edit_infomation_item),
                    contentDescription = "Изменить информацию"
                )
            }
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Папка 1",
                fontSize = 18.sp,
                fontFamily = montserrat_alternates_italic_font
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp ,top = 16.dp, end = 16.dp)
                    .border(.1.dp, Color.Black, RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SoftOrange)
                        .padding(16.dp)
                ) {
                    if (photoUrl != null) {
                        Image(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            painter = photoUrl,
                            contentDescription = "Фото готового блюда"
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
                        fontSize = 14.sp,
                        fontFamily = montserrat_alternates_italic_font,
                        text = "Пирог с вишней в духовке"
                    )
                }
            }
        }
    }
}