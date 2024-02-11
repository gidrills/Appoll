package com.example.appoll.ui.scaffolds

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.example.appoll.ui.BottomNavGraph
import com.example.appoll.ui.appbars.AppollBar2
import com.example.appoll.ui.appbars.AppollBottomBar
import com.example.appoll.ui.appbars.MainBar
import com.example.appoll.ui.screens.PollScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollScaffold(modifier: Modifier, navController: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold (
        topBar={
            AppollBar2(navController, modifier, scrollBehavior)
        } ,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            AppollBottomBar(navController = navController)
        }
    ){
        PollScreen(modifier = modifier.padding(it), navController = navController)
    }
}