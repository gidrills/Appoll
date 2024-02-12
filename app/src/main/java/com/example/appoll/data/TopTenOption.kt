package com.example.appoll.data

import com.thedeanda.lorem.LoremIpsum

data class TopTenOption(
    val title: String,
    val description: String,
    val likes : Int
)

fun generateRandomOption():TopTenOption{
    val lorem = LoremIpsum.getInstance()
    val randomLikes = (0..100).random()

    return TopTenOption(
        generateRandomWordsString(lorem,3),
        generateRandomWordsString(lorem,15),
        randomLikes
    )
}

val topTenOptions = listOf<TopTenOption>(
    generateRandomOption(),
    generateRandomOption(),
    generateRandomOption(),
    generateRandomOption(),
)