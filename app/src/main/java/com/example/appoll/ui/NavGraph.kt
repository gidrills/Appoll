package com.example.appoll.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appoll.ui.scaffolds.HomeScaffold
import com.example.appoll.ui.scaffolds.InsertOptionPollScaffold
import com.example.appoll.ui.scaffolds.MyPollsScaffold
import com.example.appoll.ui.scaffolds.PollScaffold
import com.example.appoll.ui.screens.LoginScreen
import com.example.appoll.ui.screens.Screens
import com.example.appoll.ui.screens.SignupScreen
import com.example.appoll.ui.viewmodel.AuthState
import com.example.appoll.ui.viewmodel.AuthViewModel

@Composable
fun BottomNavGraph(navController: NavHostController,modifier:Modifier, authViewModel: AuthViewModel){
    NavHost(navController=navController,
        startDestination = "login"){
        //homeGraph(navController,modifier
        composable(route = Screens.Home.route) {
            HomeScaffold(modifier,navController, authViewModel)
        }
        composable(route = Screens.Settings.route){
            ForYouScreen(modifier)
        }
        composable(route = Screens.MyPolls.route){
            //CreatePollScreen(modifier)
            MyPollsScaffold(modifier, navController, authViewModel)
        }
        composable(route = Screens.InsertOptionPoll.route){
            //CreatePollScreen(modifier)
            InsertOptionPollScaffold(modifier, navController)
        }
        composable(route = Screens.InBox.route){
            ProfileScreen(modifier, authViewModel, navController)
        }
        composable(route = Screens.Poll.route + "/{pollId}/{pollTitle}"){
                backStackEntry->
                val pollId = backStackEntry.arguments?.getString("pollId")
                val pollTitle = backStackEntry.arguments?.getString("pollTitle")
                PollScaffold(modifier, navController, pollId, pollTitle, authViewModel)
        }
        composable("login") {
            LoginScreen(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupScreen(modifier, navController, authViewModel)
        }

    }
}

/*@Composable
fun AuthenticationNavigation(modifier: Modifier, authViewModel: AuthViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){

    }
}*/

@Composable
fun ForYouScreen(modifier: Modifier) {
    Text(text = "ForYou",modifier)
}

@Composable
fun ProfileScreen(modifier: Modifier, authViewModel: AuthViewModel, navController: NavHostController) {
    Text(text = "InBox",modifier)
    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign out")
        }
    }
}
/*@Composable
fun CreatePollScreen(modifier: Modifier) {
    Text(text = "Create Poll",modifier)
}*/
