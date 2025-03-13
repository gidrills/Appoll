package com.example.appoll.data

import com.thedeanda.lorem.LoremIpsum

object LocalDataSource {
    fun generateRandomOption():PollChoice{
        val lorem = LoremIpsum.getInstance()
        val randomLikes = (0..100).random()

        return PollChoice(
            generateRandomWordsString(lorem,3),
            generateRandomWordsString(lorem,15),
            randomLikes
        )
    }

    val pollOptions = listOf(
        generateRandomOption(),
        generateRandomOption(),
        generateRandomOption(),
        generateRandomOption(),
        generateRandomOption(),
        generateRandomOption(),
    )
    var pollOptionsSortedByUser = pollOptions
}
