package com.example.appoll.ui.state

import androidx.annotation.DrawableRes
import java.util.UUID

data class PollsItemUiState(
    @DrawableRes  val imageResourceId: Int,
    val id : UUID,
    val title: String,
    val body : String,
    val likes : Int,
    val comments : Int,
    val participantCount: Int
)
