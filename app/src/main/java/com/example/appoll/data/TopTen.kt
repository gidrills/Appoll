package com.example.appoll.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.appoll.R

data class TopTen(
    @DrawableRes val imageResourceId: Int,
    val title: String, // Name of the top ten
    val participantCount: Int, // Number of people who participated up now
    val description: String // Description of the top ten
)
val topTens = listOf(
    TopTen(R.drawable.sneakers,"Top Ten sneakers 2023",323,"Le migliori sneakers del 2023"),
    TopTen(R.drawable.topgames2023,"Top Games 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Top anime 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best cities to visit 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best cities to visit in Italy", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best Smartphones 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best movie 2023", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best soccer player ever", 1232, "I migliori videogiochi del 2023"),
    TopTen(R.drawable.topgames2023,"Best movie 2023", 1232, "I migliori videogiochi del 2023")
)
