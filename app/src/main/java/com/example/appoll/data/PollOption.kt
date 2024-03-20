package com.example.appoll.data

import com.thedeanda.lorem.LoremIpsum

data class PollOption(
    val title: String,
    val body: String,
    val likes : Int
)

fun generateRandomOption():PollOption{
    val lorem = LoremIpsum.getInstance()
    val randomLikes = (0..100).random()

    return PollOption(
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