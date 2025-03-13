package com.example.appoll.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appoll.ui.screens.homescreens.TopTenItem
import com.example.appoll.ui.viewmodel.HomeViewModel

@Composable
fun MyPollsScreen(modifier: Modifier,
               navController : NavHostController
) {
    val viewModel = HomeViewModel()
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    viewModel.fetchPolls()

    LazyColumn(contentPadding = PaddingValues(10.dp),
        modifier = modifier){
        items(viewModel.uiState.pollsItems){
            TopTenItem(poll = it,
                selectedIndex=selectedIndex,
                onItemSelected = {index -> selectedIndex = index},
                onClick = {navController.navigate(Screens.Poll.route+ "/" + it.id+"/"+ it.title)})
        }
    }
}