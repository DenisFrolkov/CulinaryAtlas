package com.den.culinaryatlas.navigatingscreens

sealed class NavigationRoute(val route: String) {
    object AuthorizationScreen : NavigationRoute("authorization_screen")
    object RegistrationScreen : NavigationRoute("registration_screen")
    object BottomnavigationScreens : NavigationRoute("bottomnavigation_screen")
}
