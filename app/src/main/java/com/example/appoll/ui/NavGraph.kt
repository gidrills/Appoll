package com.example.appoll.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.appoll.ui.screens.Screens
import com.example.appoll.ui.screens.HomeScreen
import com.example.appoll.ui.screens.PollScreen

@Composable
fun BottomNavGraph(navController: NavHostController,modifier:Modifier){
    NavHost(navController=navController,
        startDestination = Screens.Home.route){
        //homeGraph(navController,modifier
        composable(route = Screens.Home.route) {
            HomeScreen(modifier,navController)
        }
        composable(route = Screens.Settings.route){
            SettingScreen(modifier)
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(modifier)
        }
        composable(route = Screens.Poll.route){
            PollScreen(modifier = modifier)
        }
    }
}

@Composable
fun SettingScreen(modifier: Modifier) {
    Text(text = "Setting",modifier)
}

@Composable
fun ProfileScreen(modifier: Modifier) {
    Text(text = "Profile",modifier)
}
