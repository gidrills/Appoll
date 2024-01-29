package com.example.appoll.ui.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appoll.R
import com.example.appoll.data.topTens
import com.example.appoll.data.TopTen
import com.example.appoll.ui.theme.AppollTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier,navController : NavHostController) {

    LazyColumn(contentPadding = PaddingValues(10.dp),
        modifier = modifier){
        items(topTens){
            TopTenItem2(topTen = it)
        }
    }
}
@Composable
fun FilledButtonExample(modifier: Modifier,onClick: () -> Unit) {
    Button(modifier = modifier, onClick = { onClick() }) {
        Text("Filled")
    }
}
@Composable
fun TopTenItem(
    topTen: TopTen,
    modifier: Modifier = Modifier
){

    Card(modifier = modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            TopTenIcon(topTen.imageResourceId)
            TopTenInformation(topTen.title, topTen.participantCount, modifier)
        }
    }
}

@Composable
fun TopTenItem2(
    topTen: TopTen,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.height(100.dp).fillMaxWidth().padding(5.dp)
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(R.drawable.topgames2023),
                contentDescription = null,
                modifier = Modifier.weight(0.33f)
            )
            Text(text = "asdas")
        }

        Divider()

        Row(modifier = Modifier.weight(0.25f, true)) {
            Icon(
                painter = painterResource(R.drawable.ballot_24px),
                contentDescription = "Ballot Icon",
            )
            Text(text = "1900")
        }
    }
}


@Composable
fun TopTenIcon(
    @DrawableRes topTenIcon: Int,
    modifier: Modifier=Modifier
){
    Image(
        modifier = modifier
            .padding(2.dp),
        painter = painterResource(topTenIcon),
        contentDescription = null
    )
}

@Composable
fun TopTenInformation(
    topTenTitle: String,
    participantCount: Int,
    modifier: Modifier = Modifier
){
    Log.d("NomeTag", participantCount.toString())
    Column(modifier=modifier) {
        Text(
            text = topTenTitle,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.participant_count, participantCount)
        )
    }
}
