package com.den.culinaryatlas.bottomnavigation

import com.den.culinaryatlas.R

sealed class BottomItem(
    val icon: Int,
    val route: String
) {
    object SearchScreen: BottomItem(R.drawable.apron_item, Route.SearchScreen.route)
    object CookbookScreen: BottomItem(R.drawable.apron_item, Route.CookbookScreen.route)
    object ProfileScreen: BottomItem(R.drawable.icon_people, Route.ProfileScreen.route)
}
