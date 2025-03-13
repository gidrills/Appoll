package com.example.appoll.ui.state

data class PollOptionItem(
    val title: String,
    val body: String,
    val likes : Int
)
sealed class PollChoicesUiState {
    object Loading :  PollChoicesUiState()
    data class Success(val pollOptionItems: List<PollOptionItem>, val message: String = "") : PollChoicesUiState()
    data class Error(val message: String) : PollChoicesUiState()
}

sealed class UserPollRankingUiState {
    object Loading :  UserPollRankingUiState()
    data class Success(val pollOptionsSortedByUserItems: List<PollOptionItem>, val message: String = "") : UserPollRankingUiState()
    data class Error(val message: String) : UserPollRankingUiState()
}
