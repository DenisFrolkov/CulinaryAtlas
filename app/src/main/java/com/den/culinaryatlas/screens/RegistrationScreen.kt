package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.culinaryatlas.R
import com.den.culinaryatlas.ui.theme.Orange1
import com.den.culinaryatlas.ui.theme.Orange2
import com.den.culinaryatlas.ui.theme.Orange3

@Preview
@Composable
fun RegistrationScreen() {
    val roundedFont = FontFamily(Font(R.font.brushscriptmtrusbyme_italic))
    val normalFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val backgroundPainter = painterResource(id = R.drawable.login_window_background)
    val iconLogo = painterResource(id = R.drawable.logo_icon)
    var login by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var rePassword by remember { mutableStateOf(TextFieldValue()) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Orange1),
        contentColor = Color.Black
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = backgroundPainter,
            contentDescription = "Application background",
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(230.dp)
                    .padding(105.dp, 68.dp, 105.dp, 0.dp),
                painter = iconLogo,
                contentDescription = "Logo icon"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(274.dp, 86.dp)
                    .padding(0.dp, 20.dp, 0.dp, 15.dp),
                text = "Добро пожаловать!",
                textAlign = TextAlign.Center,
                fontSize = 44.sp,
                fontFamily = roundedFont,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp, 15.dp, 35.dp, 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .size(290.dp, 56.dp)
                    .background(
                        color = Orange2,
                        shape = RoundedCornerShape(size = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = login,
                    onValueChange = {
                        login = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp, 20.dp, 0.dp),
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp, 8.dp, 35.dp, 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,

                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .size(290.dp, 56.dp)
                    .background(
                        color = Orange2,
                        shape = RoundedCornerShape(size = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp, 20.dp, 0.dp),
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp, 8.dp, 35.dp, 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,

                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .size(290.dp, 56.dp)
                    .background(
                        color = Orange2,
                        shape = RoundedCornerShape(size = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp, 20.dp, 0.dp),
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp, 8.dp, 35.dp, 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,

                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .size(290.dp, 56.dp)
                    .background(
                        color = Orange2,
                        shape = RoundedCornerShape(size = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = rePassword,
                    onValueChange = {
                        rePassword = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp, 20.dp, 0.dp),
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                    )
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(44.dp, 25.dp, 44.dp, 2.dp)
                    .size(272.dp, 56.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    Orange3
                )
            ) {
                Text(
                    modifier = Modifier
                        .size(254.dp, 56.dp)
                        .wrapContentSize(Alignment.Center),
                    text = "Зарегистрироваться",
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    fontFamily = roundedFont,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp, 0.dp, 0.dp),
                text = "Вернуть на экран авторизации?",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = normalFont,
            )
        }
    }
}