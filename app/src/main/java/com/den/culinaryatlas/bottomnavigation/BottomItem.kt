package com.den.culinaryatlas.bottomnavigation

import com.den.culinaryatlas.R

sealed class BottomItem(
    val icon: Int,
    val route: String
) {
    object SearchScreen: BottomItem(R.drawable.icon_search, "search")
    object CookbookScreen: BottomItem(R.drawable.icon_book, "cookbook")
    object ProfileScreen: BottomItem(R.drawable.icon_people, "profile")
}
