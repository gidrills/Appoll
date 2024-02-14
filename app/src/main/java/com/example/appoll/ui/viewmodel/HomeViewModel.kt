package com.example.appoll.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appoll.data.PollRepository
import com.example.appoll.ui.state.PollUiState
import com.example.appoll.ui.state.PollsItemUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository : PollRepository = PollRepository()

    var uiState by mutableStateOf(PollUiState())
        private set

    private var fetchJob : Job? = null
    fun fetchPolls(){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val pollsItemsG = repository.polls
                val pollsItems = pollsItemsG.map { poll ->
                    PollsItemUiState(
                        imageResourceId = poll.imageResourceId,
                        id = poll.id,
                        title = poll.title,
                        body = poll.description,
                        likes = poll.likes,
                        comments = poll.comments,
                        participantCount = poll.participantCount
                    )
                }
                uiState = uiState.copy(
                    pollsItems = pollsItems)
            } catch (e: Exception) {
                // Gestisci eventuali eccezioni qui
                Log.e("ERROR", "Errore durante il recupero dei sondaggi", e)
            }
        }

    }
}