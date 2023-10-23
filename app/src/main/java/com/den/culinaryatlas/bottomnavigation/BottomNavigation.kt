package com.den.culinaryatlas.bottomnavigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.den.culinaryatlas.ui.theme.Orange1

@Composable
fun BottomNavigation(navController: NavController) {
    val listItems = listOf(
        BottomNavigationItem.SearchScreen,
        BottomNavigationItem.CookbookScreen,
        BottomNavigationItem.ProfileScreen
    )

    BottomNavigation(
        modifier = Modifier
            .height(60.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        backgroundColor = Orange1
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        modifier = Modifier.size(38.dp, 38.dp)
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black
            )
        }
    }
}

