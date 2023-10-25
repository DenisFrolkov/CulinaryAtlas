package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.culinaryatlas.R
import com.den.culinaryatlas.ui.theme.SoftOrange

@Preview
@Composable
fun ProfileScreen() {
    val normalFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column() {
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.profile_background),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                    )
                    Column(
                        modifier = Modifier.padding(top = 26.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .wrapContentSize()
                                .size(110.dp)
                                .clip(shape = RoundedCornerShape(12.dp)),
                            painter = painterResource(id = R.drawable.profile_image),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 26.dp, bottom = 4.dp),
                            text = "Den12213",
                            fontSize = 18.sp,
                            fontFamily = normalFont
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 4.dp, bottom = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(end = 34.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .size(30.dp),
                                    painter = painterResource(id = R.drawable.profile_myrecipe_icon),
                                    contentDescription = null
                                )
                                Text(
                                    text = "4",
                                    fontSize = 18.sp,
                                    fontFamily = normalFont
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .padding(start = 34.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .size(30.dp)
                                        .padding(end = 4.dp),
                                    painter = painterResource(id = R.drawable.profile_savedrecipe_icon),
                                    contentDescription = null
                                )
                                Text(
                                    text = "7",
                                    fontSize = 18.sp,
                                    fontFamily = normalFont
                                )
                            }
                        }
                    }
                }
                Image(
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .size(24.dp)
                        .align(Alignment.TopEnd),
                    painter = painterResource(id = R.drawable.profile_exit_icon),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(start = 30.dp, top = 16.dp, bottom = 8.dp),
                text = "О себе:",
                fontSize = 16.sp,
                fontFamily = normalFont
            )
            Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                var text by remember { mutableStateOf(TextFieldValue()) }
                Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SoftOrange),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp
                    ),
                    maxLines = 10,
                )
            }
            Button(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = {  },
                colors = ButtonDefaults.buttonColors(SoftOrange)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.profile_setting_icon),
                    contentDescription = null,
                    tint = Color.Black
                )
                Text(
                    text = "Настройки",
                    color = Color.Black
                )
            }
        }
    }
}
