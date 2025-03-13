package com.example.appoll.ui.screens.createpollscreens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertPollInfoScreen(modifier: Modifier,
                         isNextArrowActive: Boolean,
                         onNextArrowActiveChange: (Boolean) -> Unit){

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = title,
            onValueChange = { newText ->
                title = newText
                onNextArrowActiveChange(!newText.isNullOrBlank() && !description.isNullOrBlank())
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
                onNextArrowActiveChange(!newText.isNullOrBlank() && !description.isNullOrBlank())
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
        PhotoPickerScreen(isNextArrowActive, onNextArrowActiveChange)
    }

}
@Composable
fun PhotoPickerScreen(isNextArrowActive: Boolean,
                      onNextArrowActiveChange: (Boolean) -> Unit){

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
        })

    Surface(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(
                text = "Choose cover image",
                /*style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )*/
            )
            Spacer(modifier = Modifier.size(20.dp))
            
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Add Image"
                    )
                    Text(
                        text = "Pick a photo",
                        style = TextStyle(
                            fontSize = 18.sp
                        )

                    )
                }
                
            }

            AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(8.dp)),
                model = selectedImageUri,
                contentDescription = null,
                contentScale = ContentScale.FillBounds)
        }
    }
}