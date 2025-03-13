package com.example.appoll.ui.appbars.searchbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

const val placeholder = "PlaceHolder"

@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                ,
            contentAlignment = Alignment.CenterStart
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(focusRequester),
                singleLine = true,
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.Normal,
                ),
                placeholder = {
                    Text(
                        text = "Search",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Normal,
                    )
                },
                leadingIcon = {
                    IconButton(onClick = {focusManager.clearFocus() }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(21.dp)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = onCloseClicked) {
                        if (text.isNotBlank()) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },

            )
            SideEffect {
                focusRequester.requestFocus()
            }
        }
    }
}

