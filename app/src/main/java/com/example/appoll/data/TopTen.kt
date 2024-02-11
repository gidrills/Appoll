package com.example.appoll.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.appoll.R
import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum

data class TopTen(
    @DrawableRes val imageResourceId: Int,
    val title: String, // Name of the top ten
    val participantCount: Int, // Number of people who participated up now
    val description: String, // Description of the top ten
    val likes : Int,
    val comments : Int,
    val user : String
)

val topTens = listOf(
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen(),
    generateRandomTopTen()
)

/*val topTens = listOf(
    TopTen(R.drawable.sneakers,"Top Ten sneakers 2023",323,"Le migliori sneakers del 2023"),
    TopTen(R.drawable.topgames2023,"Top Games 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Top anime 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best cities to visit 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best cities to visit in Italy", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best Smartphones 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best movie 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best soccer player ever", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best movie 2023", 1232, "I migliori videogiochi del 2023")
)*/

fun generateRandomTopTen(): TopTen {
    val titles = listOf(
        "Top Ten sneakers 2023",
        "Top Games 2023",
        "Top anime 2023",
        "Best cities to visit 2023"
    )

    val images= listOf(
        R.drawable.sneakers,
        R.drawable.topgames2023
    )
    val randomTitle = titles.random()
    val randomImage = images.random()

    val randomParticipantCount = (10..1000000).random() // Genera un numero casuale tra 1000 e 10000

    val lorem = LoremIpsum.getInstance()
    val randomDescription = "Description" + generateRandomWordsString(lorem, 35)
    val randomLikes = (0..100).random() // Genera un numero casuale tra 0 e 100
    val randomComments = (0..50).random() // Genera un numero casuale tra 0 e 50
    val randomUser = listOf("User1", "User2", "User3", "User4").random()

    return TopTen(
        randomImage,
        randomTitle,
        randomParticipantCount,
        randomDescription,
        randomLikes,
        randomComments,
        randomUser
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

