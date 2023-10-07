package com.den.culinaryatlas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
fun AuthorizationScreen() {
    val roundedFont = FontFamily(Font(R.font.brushscriptmtrusbyme_italic))
    val normalFont = FontFamily(Font(R.font.montserrat_alternates_italic))
    val backgroundPainter = painterResource(id = R.drawable.login_window_background)
    val iconLogo = painterResource(id = R.drawable.logo_icon)
    var login by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Orange1),
        contentColor = Color.Black
    )
    {
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
            horizontalAlignment = CenterHorizontally
        ) {
            Image(
                painter = iconLogo,
                contentDescription = "Logo icon",
                modifier = Modifier
                    .size(230.dp)
                    .padding(0.dp, 68.dp, 0.dp, 0.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 27.dp),
                text = "С Возвращением!",
                textAlign = TextAlign.Center,
                fontSize = 44.sp,
                fontFamily = roundedFont,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp, 27.dp, 35.dp, 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .height(56.dp)
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
                    .padding(35.dp, 8.dp, 35.dp, 4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,

                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(56.dp)
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 4.dp, 170.dp, 25.dp),
                text = "Забыли пароль?",
                fontWeight = FontWeight.Bold,
                fontFamily = normalFont
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(115.dp, 25.dp, 115.dp, 4.dp)
                    .height(50.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    Orange3
                )
            ) {
                Text(
                    modifier = Modifier
                        .height(60.dp)
                        .wrapContentSize(Alignment.Center),
                    text = "Войти",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontFamily = roundedFont,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp, 0.dp, 0.dp),
                text = "Впервые у нас? Создай аккаунт!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = normalFont
            )
        }
    }
}