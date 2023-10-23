package com.den.culinaryatlas.bottomnavigation

import com.den.culinaryatlas.R

sealed class BottomNavigationItem(
    val icon: Int,
    val route: String
) {
    object SearchScreen: BottomNavigationItem(R.drawable.apron_item, BottomNavigationRoute.SearchScreen.route)
    object CookbookScreen: BottomNavigationItem(R.drawable.icon_book, BottomNavigationRoute.CookbookScreen.route)
    object ProfileScreen: BottomNavigationItem(R.drawable.icon_people, BottomNavigationRoute.ProfileScreen.route)
}
