package com.example.appoll.ui.scaffolds

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.example.appoll.data.Poll
import com.example.appoll.ui.appbars.PollTopBar
import com.example.appoll.ui.appbars.AppollBottomBar
import com.example.appoll.ui.screens.PollScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollScaffold(modifier: Modifier, navController: NavHostController, poll: Poll) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold (
        topBar={
            PollTopBar(navController, modifier, scrollBehavior, poll)
        } ,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            AppollBottomBar(navController = navController)
        }
    ){
        PollScreen(modifier = modifier.padding(it), poll)
    }
}