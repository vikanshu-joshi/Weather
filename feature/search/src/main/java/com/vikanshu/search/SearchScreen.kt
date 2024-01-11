package com.vikanshu.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    deviceSizeType: DeviceSizeType,
    searchViewModel: SearchViewModel = viewModel(),
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    SearchScreenPortrait(modifier, searchViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenPortrait(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel
) {
    val state by searchViewModel.uiState

    Column(
        modifier = modifier
    ) {
        Row {
            TopAppBar(
                title = {
                    Text(
                        "Add City",
                        fontFamily = SfDisplayProFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                }
            )
        }
    }
}
