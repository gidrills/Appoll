package com.example.appoll.ui.appbars

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppollTopBar(
    modifier: Modifier,
    navController: NavHostController,
    topAppBarState: MutableState<Boolean>
) {
    val shadowModifier = modifier.shadow(
        elevation = 3.dp,
        spotColor = Color.DarkGray,
        //shape = RoundedCornerShape(10.dp)
    )

    if (topAppBarState.value) {
        MainBar(shadowModifier)
    } else {
        AppollBar(navController, shadowModifier)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBar(modifier:Modifier){
    TopAppBar(
        title = {
            Text(
                "APPOLL",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon"
                )
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppollBar(navController: NavHostController, modifier: Modifier){
    TopAppBar(
        title = { },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon"
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back icon"
                )
            }
        },
        modifier = modifier
    )
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppollBar()
}*/