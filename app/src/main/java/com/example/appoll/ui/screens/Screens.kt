package com.example.appoll.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector?
){
    object Home: Screens(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Poll : Screens(
        route = "poll",
        title = "poll",
        icon = null
    )
    object Profile: Screens(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Settings: Screens(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
