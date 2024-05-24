package com.example.appoll.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appoll.R
import com.example.appoll.ui.state.PollOptionsItemUiState
import com.example.appoll.ui.viewmodel.PollViewModel

@Composable
fun PollScreen(modifier: Modifier, idPoll: String?) {
    val titles = listOf("Rank", "Comments", "Stats")

    val pollViewModel= PollViewModel()
    pollViewModel.fetchPollOptions(idPoll!!)

    PollOption(modifier = modifier, titles = titles, pollOptions = pollViewModel.uiState.pollOptionsItems)

}

@Composable
fun PollOption(modifier: Modifier, titles:List<String>, pollOptions: List<PollOptionsItemUiState>){
    var state by remember { mutableStateOf(0) }

    val density = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(10.dp), modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(
                    modifier = Modifier
                        .height(componentHeight) // Adjust the height as needed
                        .fillMaxWidth()
                )
            }

            items(pollOptions) {
                PollOptionItem(pollOption = it)
            }
        }

        TabRow(
            selectedTabIndex = state,
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                //.background(MaterialTheme.colorScheme.background)
                .onGloballyPositioned {
                    componentHeight = with(density) {
                        it.size.height.toDp()
                    }
                }
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollOptionItem(
    pollOption: PollOptionsItemUiState
) {
    var isThumbUpSelected by remember { mutableStateOf(false) }
    var isThumbDownSelected by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.default_image),
                contentDescription = null,
                modifier= Modifier
                    .weight(.40f)
                    .padding(5.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pollOption.title,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 15.sp
                )
                Text(
                    text = pollOption.body,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontSize = 10.sp
                )

            }
            /*Column {

            }*/

        }
        Row(
            modifier = Modifier
                .weight(0.30f, true)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            IconButton(onClick = {  isThumbUpSelected = !isThumbUpSelected
                if (isThumbUpSelected) {
                    isThumbDownSelected = false
                }
            }) {
                Icon(
                    imageVector = if (isThumbUpSelected) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp ,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { isThumbDownSelected = !isThumbDownSelected
                if (isThumbDownSelected) {
                    isThumbUpSelected = false
                }
            }) {
                Icon(
                    painter = if (isThumbDownSelected) painterResource(R.drawable.thumb_down_filled_24px) else painterResource(R.drawable.thumb_down_outlined24px),
                    contentDescription = "Localized description"
                )
            }
        }

        HorizontalDivider()
    }
}