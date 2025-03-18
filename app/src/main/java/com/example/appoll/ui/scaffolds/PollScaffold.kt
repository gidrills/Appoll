package com.example.appoll.ui.scaffolds

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appoll.ui.appbars.PollTopBar
import com.example.appoll.ui.appbars.AppollBottomBar
import com.example.appoll.ui.appbars.searchbar.MySearchBar
import com.example.appoll.ui.screens.homescreens.PollScreen
import com.example.appoll.ui.viewmodel.AuthViewModel
import com.example.appoll.ui.viewmodel.PollOptionsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollScaffold(modifier: Modifier, navController: NavHostController, idPoll: String?, pollTitle: String?, authViewModel: AuthViewModel) {
    Log.d("chiamato","asdsa")
    //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState()).also {
    //    Log.d("Debug", "scrollBehavior created")
    //}
    //true is default, false is search bar
    val pollOptionsListViewModel: PollOptionsListViewModel = viewModel()
    var selectedTopBar by rememberSaveable { mutableStateOf(true) }
    val toggleTopBar = { selectedTopBar = !selectedTopBar }
    var query by rememberSaveable { mutableStateOf("") }

    val userPollRankingFlag by pollOptionsListViewModel.userPollRankingFlag.collectAsState()


    val shadowModifier = modifier.shadow(
        elevation = 3.dp,
        spotColor = Color.DarkGray,
        //shape = RoundedCornerShape(10.dp)
    )

    Scaffold (
        topBar={if (selectedTopBar) {
            PollTopBar(navController, modifier, pollTitle, toggleTopBar)
        } else {
            MySearchBar(modifier = shadowModifier,query, { newText -> query = newText }, {query = ""})
        }
        } ,floatingActionButton = {
            Column {
                // Mostra il primo FAB solo se showFirstFab è true
                if (!userPollRankingFlag) {
                    FloatingActionButton(
                        onClick = { pollOptionsListViewModel.saveUserSortedPollOptions(idPoll!!) },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Check")
                    }
                }
                // Secondo FAB sempre visibile
                FloatingActionButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        },
        modifier = modifier,
        bottomBar = {
            AppollBottomBar(navController = navController)
        }
    ){
        Log.d("as","asd")
        PollScreen(modifier = modifier.padding(it), idPoll, pollOptionsListViewModel)
    }
}