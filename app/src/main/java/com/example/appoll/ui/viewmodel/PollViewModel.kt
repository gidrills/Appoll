package com.example.appoll.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appoll.data.PollOptionRepository
import com.example.appoll.ui.state.PollOptionUiState
import kotlinx.coroutines.Job

class PollViewModel : ViewModel(){
    private val repository = PollOptionRepository()

    var uiState by mutableStateOf(PollOptionUiState())
        private set

    private var fetchJob : Job? = null
    fun fetchPollOptions(idPoll: String){

    }
}