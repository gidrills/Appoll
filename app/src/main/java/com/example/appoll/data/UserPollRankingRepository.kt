import com.example.appoll.data.LocalDataSource
import com.example.appoll.data.PollChoice

interface UserPollRankingRepository {
    suspend fun getUserPollRanking(userId: String, pollId: String): List<PollChoice>
    suspend fun saveUserPollRanking(userId: String, pollId: String, rankedChoices: List<PollChoice>)
}

class UserPollRankingRepositoryImpl(private val localDataSource: LocalDataSource) : UserPollRankingRepository {
    override suspend fun getUserPollRanking(userId: String, pollId: String): List<PollChoice> {
        return localDataSource.pollOptionsSortedByUser
    }

    override suspend fun saveUserPollRanking(userId: String, pollId: String, rankedChoices: List<PollChoice>) {
        localDataSource.pollOptionsSortedByUser = rankedChoices
    }
}
