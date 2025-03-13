package com.example.appoll.ui.scaffolds

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.example.appoll.ui.appbars.InsertPollTopBar
import com.example.appoll.ui.screens.createpollscreens.InsertOptionPollScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertOptionPollScaffold(modifier: Modifier, navController: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var isNextArrowActive by remember { mutableStateOf(false) }

    Scaffold (
        topBar={
            InsertPollTopBar(navController, modifier, isNextArrowActive)
        }
        ,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ){
        InsertOptionPollScreen(modifier = modifier.padding(it),
            isNextArrowActive,
            onNextArrowActiveChange = { isActive -> isNextArrowActive = isActive })
    }
}