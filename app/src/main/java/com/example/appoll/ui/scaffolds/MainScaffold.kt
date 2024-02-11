package com.example.appoll.ui.scaffolds

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appoll.ui.BottomNavGraph
import com.example.appoll.ui.appbars.AppollBottomBar
import com.example.appoll.ui.appbars.MainBar
import com.example.appoll.ui.screens.HomeScreen

@Composable
fun MainScaffold(modifier: Modifier, navController: NavHostController){

    val shadowModifier = modifier.shadow(
        elevation = 3.dp,
        spotColor = Color.DarkGray,
        //shape = RoundedCornerShape(10.dp)
    )

    Scaffold (
        topBar={
            MainBar(shadowModifier)
        } ,
        bottomBar = {
            AppollBottomBar(navController = navController)
        }
    ){
        HomeScreen(modifier = modifier.padding(it), navController = navController)
    }
}