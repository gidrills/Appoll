package com.example.appoll.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.twotone.DateRange
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
    object InBox: Screens(
        route = "InBox",
        title = "InBox",
        icon = Icons.Default.Person
    )

    object Temporary: Screens(
        route = "Temporary",
        title = "Temporary",
        icon = Icons.TwoTone.DateRange
    )
    object Settings: Screens(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object MyPolls: Screens(
        route = "myPolls",
        title = "My polls",
        icon = Icons.Rounded.Edit
    )

    object InsertOptionPoll: Screens(
        route = "insertOptionPoll",
        title = "Create Poll",
        icon = Icons.Rounded.Edit
    )
}
