package com.example.appoll.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appoll.ui.scaffolds.HomeScaffold
import com.example.appoll.ui.scaffolds.InsertOptionPollScaffold
import com.example.appoll.ui.scaffolds.MyPollsScaffold
import com.example.appoll.ui.scaffolds.PollScaffold
import com.example.appoll.ui.screens.Screens

@Composable
fun BottomNavGraph(navController: NavHostController,modifier:Modifier){
    NavHost(navController=navController,
        startDestination = Screens.Home.route){
        //homeGraph(navController,modifier
        composable(route = Screens.Home.route) {
            HomeScaffold(modifier,navController)
        }
        composable(route = Screens.Settings.route){
            ForYouScreen(modifier)
        }
        composable(route = Screens.MyPolls.route){
            //CreatePollScreen(modifier)
            MyPollsScaffold(modifier, navController)
        }
        composable(route = Screens.InsertOptionPoll.route){
            //CreatePollScreen(modifier)
            InsertOptionPollScaffold(modifier, navController)
        }
        composable(route = Screens.InBox.route){
            ProfileScreen(modifier)
        }
        composable(route = Screens.Poll.route + "/{pollId}/{pollTitle}"){
                backStackEntry->
                val pollId = backStackEntry.arguments?.getString("pollId")
                val pollTitle = backStackEntry.arguments?.getString("pollTitle")
                PollScaffold(modifier, navController, pollId, pollTitle)
        }

    }
}

@Composable
fun ForYouScreen(modifier: Modifier) {
    Text(text = "ForYou",modifier)
}

@Composable
fun ProfileScreen(modifier: Modifier) {
    Text(text = "InBox",modifier)
}
/*@Composable
fun CreatePollScreen(modifier: Modifier) {
    Text(text = "Create Poll",modifier)
}*/
