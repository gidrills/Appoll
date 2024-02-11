package com.example.appoll.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appoll.ui.scaffolds.SelectedScaffold
import com.example.appoll.ui.screens.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier){

    val navController = rememberNavController()

    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screens.Poll.route -> {
            topBarState.value = false
        }
        else -> {
            topBarState.value = true
        }
    }
    SelectedScaffold(modifier, navController)


}
