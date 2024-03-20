package com.example.appoll.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appoll.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.appoll.data.Poll
import com.example.appoll.ui.state.PollsItemUiState
import com.example.appoll.ui.theme.AppollTheme
import com.example.appoll.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(modifier: Modifier,
               navController : NavHostController) {

    val viewModel = HomeViewModel()
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    viewModel.fetchPolls()

    LazyColumn(contentPadding = PaddingValues(10.dp),
        modifier = modifier){
        items(viewModel.uiState.pollsItems){
            TopTenItem(poll = it,
                selectedIndex=selectedIndex,
                onItemSelected = {index -> selectedIndex = index},
                onClick = {navController.navigate(Screens.Poll.route+ "/" + it.id+"/"+ it.title)})
        }
    }
}
@Composable
fun votesIconButton(poll: PollsItemUiState) {

    val textFontSize = 12.sp
    val percentageShift = 0.06f
    val iconOffset = textFontSize.value * percentageShift
    OutlinedButton(onClick = { /* ... */ }, modifier = Modifier.height(36.dp), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.outline_poll_24),
                contentDescription = "Ballot Icon",
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .offset(y = iconOffset.dp)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(textAlign = TextAlign.Start, text = poll.participantCount.toString(), fontSize = textFontSize)
        }

    }
}
@Composable
fun commentsIconButton(poll: PollsItemUiState) {

    val textFontSize = 12.sp
    val percentageShift = 0.115f
    val iconOffset = textFontSize.value * percentageShift

    OutlinedButton(onClick = { /* ... */ }, modifier = Modifier.height(36.dp), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.outline_mode_comment_24),
                contentDescription = "Comment Icon",
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .offset(y = iconOffset.dp)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(textAlign = TextAlign.Start, text = poll.comments.toString(), fontSize = 12.sp)
        }

    }
}



@Composable
fun TopTenItem(
    poll: PollsItemUiState,
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    onClick:()->Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        //onClick={Log.d("cardClicked","clicked")},
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(
                onClick = onClick,
            )

    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = poll.title,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 20.sp
                )
                Text(
                    text = poll.body,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 14.sp
                )

            }
            Image(
                painter = painterResource(poll.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .weight(.40f)
                    .padding(5.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                contentScale = ContentScale.Crop
            )

        }
        Row(
            modifier = Modifier
                .weight(0.30f, true)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            votesIconButton(poll)
            commentsIconButton(poll)
        }
        HorizontalDivider(modifier = Modifier.padding(4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppollTheme { // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(Modifier, rememberNavController())
        }
    }
}


