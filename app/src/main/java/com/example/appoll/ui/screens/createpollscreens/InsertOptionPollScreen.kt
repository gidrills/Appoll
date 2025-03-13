package com.example.appoll.ui.screens.createpollscreens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appoll.ui.state.PollsItemUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertOptionPollScreen(modifier: Modifier,
                         isNextArrowActive: Boolean,
                         onNextArrowActiveChange: (Boolean) -> Unit) {
    val pollsItems: MutableList<Int> = mutableListOf(1)

    LazyColumn(modifier = modifier.fillMaxSize()){
        items(pollsItems){
        NewOptionItem()
            HorizontalDivider(modifier = Modifier.padding(4.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOptionItem(){
    var description by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }

    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface)) {
        OutlinedTextField(
            value = title,
            onValueChange = { newText ->
                title = newText
            },
            singleLine = true,
            label = { Text("Enter Title") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue, // Custom focused border color
                unfocusedBorderColor = Color.Gray // Custom unfocused border color
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { newText ->
                description = newText
            },
            singleLine = false,
            label = { Text("Enter Description") },
            /*colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue, // Custom focused border color
                unfocusedBorderColor = Color.Gray // Custom unfocused border color
            ),*/
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}