package com.den.culinaryatlas.bottomnavigation

sealed class Route(val route: String) {
    object SearchScreen : Route("search_screen")
    object CookbookScreen : Route("cookbook_screen")
    object ProfileScreen : Route("profile_screen")
}
