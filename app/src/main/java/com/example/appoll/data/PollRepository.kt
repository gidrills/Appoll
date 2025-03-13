package com.example.appoll.data

import java.util.UUID
import com.example.appoll.data.polls
import com.example.appoll.ui.state.PollsItemUiState

class PollRepository {

    val polls : List<Poll> = com.example.appoll.data.polls
    fun getPollById(id: UUID) : Poll?{
        return polls.find { it.id==id }
    }
}