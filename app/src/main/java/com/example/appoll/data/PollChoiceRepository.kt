package com.example.appoll.data

import kotlinx.coroutines.delay

interface PollChoiceRepository{
    suspend fun getPollChoicesByPollId(id: String): List<PollChoice>
}
class PollChoiceRepositoryImpl(private val localDataSource: LocalDataSource) : PollChoiceRepository {
    override suspend fun getPollChoicesByPollId(id: String): List<PollChoice> {
        delay(500)
        return localDataSource.pollOptions
    }
}