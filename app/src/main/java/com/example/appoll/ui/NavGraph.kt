package com.example.appoll.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appoll.data.getTopTenById
import com.example.appoll.ui.scaffolds.MainScaffold
import com.example.appoll.ui.scaffolds.PollScaffold
import com.example.appoll.ui.screens.Screens
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph(navController: NavHostController,modifier:Modifier){
    NavHost(navController=navController,
        startDestination = Screens.Home.route){
        //homeGraph(navController,modifier
        composable(route = Screens.Home.route) {
            MainScaffold(modifier,navController)
        }
        composable(route = Screens.Settings.route){
            SettingScreen(modifier)
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(modifier)
        }
        composable(route = Screens.Poll.route + "/{pollId}"){ backStackEntry->
            val pollId = backStackEntry.arguments?.getString("pollId")
            PollScaffold(modifier, navController, getTopTenById(UUID.fromString(pollId))!!)
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
