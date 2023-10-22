package com.den.culinaryatlas.navigatingscreens

sealed class Route(val route: String) {
    object AuthorizationScreen : Route("authorization_screen")
    object RegistrationScreen : Route("registration_screen")
    object BottomnavigationScreens : Route("bottomnavigation_screen")
}
