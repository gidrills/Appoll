package com.example.appoll.ui.scaffolds

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.appoll.ui.BottomNavGraph

@Composable
fun SelectedScaffold(modifier: Modifier, navController: NavHostController){

    BottomNavGraph(navController = navController, modifier)

}