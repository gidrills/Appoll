package com.example.appoll.data

import androidx.annotation.DrawableRes
import com.example.appoll.R
import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum
import java.util.UUID

data class Poll(
    val id: UUID,
    @DrawableRes val imageResourceId: Int,
    val title: String, // Name of the top ten
    val participantCount: Int, // Number of people who participated up now
    val description: String, // Description of the top ten
    val likes : Int,
    val comments : Int,
    val user : String,
    val options: List<PollOption>
)
val polls = List(7) { index ->
    val title = when (index) {
        0 -> "Top Ten sneakers 2023"
        1 -> "Top Games 2023"
        2 -> "Top anime 2023"
        3 -> "Best cities to visit 2023"
        4 -> "Top Rugby player 2023"
        5 -> "Best Dota2 proplayer of the year - 2023"
        6 -> "Best movie 2023"
        else -> "Title"
    }
    val imageResource = when (index) {
        0 -> R.drawable.sneakers
        1 -> R.drawable.topgames2023
        2 -> R.drawable.anime
        3 -> R.drawable.cities
        4 -> R.drawable.rugby
        5 -> R.drawable.dota2
        6 -> R.drawable.movies
        else -> R.drawable.default_image
    }
    generateRandomTopTen(title, imageResource)
}

fun generateRandomTopTen(title: String, imageResourceId: Int): Poll {
    val id = UUID.randomUUID()
    val randomParticipantCount = (10..1000000).random() // Genera un numero casuale tra 1000 e 10000

    val lorem = LoremIpsum.getInstance()
    val randomDescription = "Description" + generateRandomWordsString(lorem, 35)
    val randomLikes = (0..100).random() // Genera un numero casuale tra 0 e 100
    val randomComments = (0..50).random() // Genera un numero casuale tra 0 e 50
    val randomUser = listOf("User1", "User2", "User3", "User4").random()

    return Poll(
        id,
        imageResourceId,
        title,
        randomParticipantCount,
        randomDescription,
        randomLikes,
        randomComments,
        randomUser,
        pollOptions
    )
}

fun generateRandomWordsString(lorem: Lorem, count: Int): String {
    val wordsList = mutableListOf<String>()
    repeat(count) {
        val word = lorem.getWords(1)
        wordsList.add(word)
    }
    return wordsList.joinToString(" ")
}

