package com.example.appoll.ui.viewmodel

import UserPollRankingRepositoryImpl
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appoll.data.LocalDataSource
import com.example.appoll.data.PollChoice
import com.example.appoll.data.PollChoiceRepositoryImpl
import com.example.appoll.ui.state.PollOptionItem
import com.example.appoll.ui.state.PollChoicesUiState
import com.example.appoll.ui.state.UserPollRankingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PollOptionsListViewModel : ViewModel() {
    private val pollChoiceRepository = PollChoiceRepositoryImpl(LocalDataSource)
    private val userPollRankingRepository = UserPollRankingRepositoryImpl(LocalDataSource)

    private val _pollChoicesUiState = MutableStateFlow<PollChoicesUiState>(PollChoicesUiState.Loading)
    val pollChoicesUiState: StateFlow<PollChoicesUiState> = _pollChoicesUiState

    private val _userPollRankingUiState = MutableStateFlow<UserPollRankingUiState>(UserPollRankingUiState.Loading)
    val userPollRankingUiState: StateFlow<UserPollRankingUiState> = _userPollRankingUiState

    private var _originalUserPollRanking: List<PollOptionItem> = emptyList()
    private val _userPollRankingFlag = MutableStateFlow(true)
    val userPollRankingFlag: StateFlow<Boolean> = _userPollRankingFlag

    init {
        Log.d("POLL_VIEW_MODEL","costruttore chiamato")
    }
    fun fetchPollChoices(idPoll: String) {
        viewModelScope.launch {
            _pollChoicesUiState.value = PollChoicesUiState.Loading
            try {
                val pollOptions = pollChoiceRepository.getPollChoicesByPollId(idPoll)
                val options = pollOptions.map { poll ->
                    PollOptionItem(
                        title = poll.title,
                        body = poll.body,
                        likes = poll.likes,
                    )
                }
                _pollChoicesUiState.value = PollChoicesUiState.Success(options)
            } catch (e: Exception) {
                // Gestisci eventuali eccezioni qui
                Log.e("ERROR", "Errore durante il recupero delle options", e)
            }
        }

    }
    fun fetchPollOptionsSortedByUser(idPoll: String) {
        viewModelScope.launch {
            _userPollRankingUiState.value = UserPollRankingUiState.Loading
            try {
                val pollOptions = userPollRankingRepository.getUserPollRanking(idPoll,"4")
                val options = pollOptions.map { poll ->
                    PollOptionItem(
                        title = poll.title,
                        body = poll.body,
                        likes = poll.likes,
                    )
                }
                _originalUserPollRanking = options
                _userPollRankingUiState.value = UserPollRankingUiState.Success(options)
            } catch (e: Exception) {
                // Gestisci eventuali eccezioni qui
                Log.e("ERROR", "Errore durante il recupero delle options", e)
            }
        }

    }

    private fun checkIfNewRankingIsDifferent() {
        if (userPollRankingUiState.value is UserPollRankingUiState.Success) {
            val successState = userPollRankingUiState.value as UserPollRankingUiState.Success

            val isDifferent = _originalUserPollRanking == successState.pollOptionsSortedByUserItems
            _userPollRankingFlag.value = isDifferent

            // Logga il risultato del confronto
            Log.d("RankingCheck", "I ranking sono uguali? $isDifferent")

            // Logga il contenuto delle liste
            Log.d("RankingCheck", "Original: $_originalUserPollRanking")
            Log.d("RankingCheck", "Current: ${successState.pollOptionsSortedByUserItems}")
        }
    }


    fun saveUserSortedPollOptions(idPoll: String){
        viewModelScope.launch {
            val currentState = _userPollRankingUiState.value
            if (currentState is UserPollRankingUiState.Success) {
                val options = currentState.pollOptionsSortedByUserItems.map { poll ->
                    PollChoice(
                        title = poll.title,
                        body = poll.body,
                        likes = poll.likes,
                    )
                }
                userPollRankingRepository.saveUserPollRanking("4",
                    idPoll, options)
            }
        }
        fetchPollOptionsSortedByUser(idPoll)
        checkIfNewRankingIsDifferent()
    }
    fun movePollChoiceUp(index: Int) {
        val currentState = _userPollRankingUiState.value
        if (currentState is UserPollRankingUiState.Success) {
            val updatedOptions = currentState.pollOptionsSortedByUserItems.toMutableList()
            if (index > 0) {
                val temp = updatedOptions[index]
                updatedOptions[index] = updatedOptions[index - 1]
                updatedOptions[index - 1] = temp

                _userPollRankingUiState.value = UserPollRankingUiState.Success(
                    pollOptionsSortedByUserItems = updatedOptions,
                    message = "Opzione spostata in alto: ${temp.title}"
                )
                checkIfNewRankingIsDifferent()
                Log.d("POLL_VIEW_MODEL", "Opzione spostata in alto: ${temp.title}")
            } else {
                Log.d("POLL_VIEW_MODEL", "L'opzione è già in cima: indice $index")
            }
        }
    }


    fun movePollChoiceDown(index: Int) {
        val currentState = _userPollRankingUiState.value
        if (currentState is UserPollRankingUiState.Success) {
            val updatedOptions = currentState.pollOptionsSortedByUserItems.toMutableList()

            // Controlla se l'elemento può essere spostato (non è l'ultimo)
            if (index < updatedOptions.size - 1) {
                // Scambia l'elemento con quello successivo
                val temp = updatedOptions[index]
                updatedOptions[index] = updatedOptions[index + 1]
                updatedOptions[index + 1] = temp

                // Aggiorna lo stato mantenendo il messaggio
                _userPollRankingUiState.value = UserPollRankingUiState.Success(
                    pollOptionsSortedByUserItems = updatedOptions,
                    message = "Opzione spostata in basso: ${temp.title}"
                )
                checkIfNewRankingIsDifferent()
                Log.d("POLL_VIEW_MODEL", "Opzione spostata in basso: ${temp.title}")
            } else {
                // L'opzione è già in fondo, log di debug
                Log.d("POLL_VIEW_MODEL", "L'opzione è già in fondo: indice $index")
            }
        }
    }

}