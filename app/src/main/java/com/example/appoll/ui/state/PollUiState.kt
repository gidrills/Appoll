package com.example.appoll.ui.state

import com.example.appoll.data.Poll

data class PollUiState(
    val pollsItems: List<PollsItemUiState> = listOf(),
    val isSignedIn: Boolean = false,
)