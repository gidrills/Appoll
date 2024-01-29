package com.example.appoll.ui.theme

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.appoll.R

enum class AppollScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    NewsInShort(title = R.string.news_page_title),
    Politics(title = R.string.politics_page_title),
    Polls(title = R.string.polls_page_title)
}
