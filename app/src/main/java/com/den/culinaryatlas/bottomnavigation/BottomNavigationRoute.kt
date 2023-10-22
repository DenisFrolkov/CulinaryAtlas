package com.den.culinaryatlas.bottomnavigation

sealed class BottomNavigationRoute(val route: String) {
    object SearchScreen : BottomNavigationRoute("search_screen")
    object CookbookScreen : BottomNavigationRoute("cookbook_screen")
    object ProfileScreen : BottomNavigationRoute("profile_screen")
}
