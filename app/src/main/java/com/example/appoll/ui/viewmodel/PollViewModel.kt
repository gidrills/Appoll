package com.example.appoll.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appoll.data.PollOption
import com.example.appoll.data.PollOptionRepository
import com.example.appoll.ui.state.PollOptionUiState
import com.example.appoll.ui.state.PollOptionsItemUiState
import com.example.appoll.ui.state.PollsItemUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PollViewModel : ViewModel() {
    private val repository = PollOptionRepository()

    var uiState by mutableStateOf(PollOptionUiState())
        private set

    private var fetchJob: Job? = null
    fun fetchPollOptions(idPoll: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val pollsOption = repository.getPollOptionsByPollId(idPoll)
                val options = pollsOption.map { poll ->
                    PollOptionsItemUiState(
                        title = poll.title,
                        body = poll.body,
                        likes = poll.likes,
                    )
                }
                uiState = uiState.copy(
                    pollOptionsItems = options
                )
            } catch (e: Exception) {
                // Gestisci eventuali eccezioni qui
                Log.e("ERROR", "Errore durante il recupero delle options", e)
            }
        }

    }
}