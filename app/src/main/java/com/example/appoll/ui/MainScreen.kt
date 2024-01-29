package com.example.appoll.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appoll.data.topTens
import com.example.appoll.ui.appbars.AppollBottomBar
import com.example.appoll.ui.appbars.AppollTopBar
import com.example.appoll.ui.screens.Screens


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier){

    val navController = rememberNavController()

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
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

    Scaffold (
        topBar={
            AppollTopBar(modifier,navController,topBarState)
        } ,
        bottomBar = {
            AppollBottomBar(navController = navController)
        }
    ){
        BottomNavGraph(navController = navController,modifier = modifier.padding(it))
    }
}
