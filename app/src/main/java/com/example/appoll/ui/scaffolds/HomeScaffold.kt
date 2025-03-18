package com.example.appoll.ui.scaffolds

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appoll.ui.appbars.AppollBottomBar
import com.example.appoll.ui.appbars.HomeTopBar
import com.example.appoll.ui.screens.homescreens.HomeScreen
import com.example.appoll.ui.viewmodel.AuthViewModel

@Composable
fun HomeScaffold(modifier: Modifier, navController: NavHostController, authViewModel: AuthViewModel){

    val shadowModifier = modifier.shadow(
        elevation = 3.dp,
        spotColor = Color.DarkGray,
        //shape = RoundedCornerShape(10.dp)
    )

    Scaffold (
        topBar={
            HomeTopBar(shadowModifier)
        } ,
        bottomBar = {
            AppollBottomBar(navController = navController)
        }
    ){
        HomeScreen(modifier = modifier.padding(it), navController = navController)
    }
}