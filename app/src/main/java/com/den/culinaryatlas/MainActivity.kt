package com.den.culinaryatlas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.den.culinaryatlas.bottomnavigation.NavigationBarScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationBarScreen()
        }
    }
}