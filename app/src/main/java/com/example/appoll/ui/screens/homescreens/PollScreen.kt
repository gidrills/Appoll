package com.example.appoll.ui.screens.homescreens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.appoll.ui.state.PollOptionItem
import com.example.appoll.ui.state.PollChoicesUiState
import com.example.appoll.ui.state.UserPollRankingUiState
import com.example.appoll.ui.viewmodel.PollOptionsListViewModel

@Composable
fun PollScreen(modifier: Modifier, idPoll: String?, pollOptionsListViewModel: PollOptionsListViewModel) {
    Log.e("d", "viene chiamato")
    pollOptionsListViewModel.fetchPollChoices(idPoll!!)
    pollOptionsListViewModel.fetchPollOptionsSortedByUser(idPoll!!)
    PollOption(modifier,pollOptionsListViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PollOption(
    modifier: Modifier,
    pollOptionsListViewModel: PollOptionsListViewModel,
) {
    val pollChoicesUiState by pollOptionsListViewModel.pollChoicesUiState.collectAsState()
    val userPollRankingUiState by pollOptionsListViewModel.userPollRankingUiState.collectAsState()
    when (pollChoicesUiState) {
        is PollChoicesUiState.Loading -> {
            CircularProgressIndicator(modifier = modifier) // Show a loading spinner
        }
        is PollChoicesUiState.Success -> {
            tabRow(
                modifier = modifier,
                pollOptions = (pollChoicesUiState as PollChoicesUiState.Success).pollOptionItems,
                pollOptionsSorted = (userPollRankingUiState as UserPollRankingUiState.Success).pollOptionsSortedByUserItems,
                onThumbDownClicked = { index ->
                    pollOptionsListViewModel.movePollChoiceDown(index)
                },
                onThumbUpClicked = { index ->
                    pollOptionsListViewModel.movePollChoiceUp(index)
                }
            )
        }
        else -> {
            Text(text = (pollChoicesUiState as PollChoicesUiState.Error).message)
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun tabRow(modifier: Modifier, pollOptions: List<PollOptionItem>, pollOptionsSorted: List<PollOptionItem>, onThumbDownClicked:(Int)->Unit, onThumbUpClicked: (Int) -> Unit){

    var state by remember { mutableStateOf(0) }
    val titles = listOf("Global", "My Rank", "Stats")
    val density = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }

    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            0-> LazyColumn(contentPadding = PaddingValues(10.dp), modifier = Modifier.fillMaxSize()) {
                item {
                    Spacer(
                        modifier = Modifier
                            .height(componentHeight) // Adjust the height as needed
                            .fillMaxWidth()
                    )
                }

                itemsIndexed(pollOptions) { index, pollOption ->
                    PollOptionItem2(
                        pollOption = pollOption,
                    )
                }
            }
            1 -> LazyColumn(contentPadding = PaddingValues(10.dp), modifier = Modifier.fillMaxSize()) {
                    item {
                        Spacer(
                            modifier = Modifier
                                .height(componentHeight) // Adjust the height as needed
                                .fillMaxWidth()
                        )
                    }

                    itemsIndexed(pollOptionsSorted) { index, pollOption ->
                        PollOptionItem(
                            pollOption = pollOption,
                            onThumbDownClicked = { onThumbDownClicked(index) }, // Passa l'indice quando viene cliccato
                            onThumbUpClicked = { onThumbUpClicked(index) }     // Stesso discorso per l'up
                        )
                    }
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
    pollOption: PollOptionItem,
    onThumbDownClicked: () -> Unit,
    onThumbUpClicked: () -> Unit
) {
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
                modifier = Modifier
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
        }
        Row(
            modifier = Modifier
                .weight(0.30f, true)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                Log.d("prova", "Spostamento opzione ")
                onThumbUpClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = {
                onThumbDownClicked() // Chiama il callback quando si clicca "Thumb Down"

            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Localized description"
                )
            }
        }

        HorizontalDivider()
 }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollOptionItem2(
    pollOption: PollOptionItem
) {
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
                modifier = Modifier
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
        }
        Row(
            modifier = Modifier
                .weight(0.30f, true)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

        }
        HorizontalDivider()

    }
}

