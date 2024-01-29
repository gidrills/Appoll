package com.example.appoll.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed class TopAppBarState(
    val title: String?,
    val actions: List<@Composable (Modifier) -> Unit> ,
    val onBackPressed: () -> Unit = {}
){
    object Appoll:TopAppBarState(title = null,
        actions = listOf {
            // Action
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back icon"
                )
            }
        }
    )
}
