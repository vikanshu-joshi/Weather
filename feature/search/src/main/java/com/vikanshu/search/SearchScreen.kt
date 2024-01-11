package com.vikanshu.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.colorA6A6A6

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
    val uiState by searchViewModel.uiState
    val searchQuery by searchViewModel.searchQuery

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Row {
                TopAppBar(
                    title = {
                        Text(
                            "Add City",
                            fontFamily = SfDisplayProFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "",
                                tint = if (searchQuery.length > 3) Color.Black else colorA6A6A6
                            )
                        }
                    },
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                )
            }
        }
        item {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                query = searchQuery,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "",
                        tint = if (searchQuery.length > 3) Color.Black else colorA6A6A6
                    )
                },

                onQueryChange = searchViewModel::onSearchQueryChanged,
                onSearch = searchViewModel::onSearchQueryChanged,
                active = false,
                onActiveChange = {},
                placeholder = {
                    Text(
                        text = "Enter City, Region or Place",
                        fontFamily = SfDisplayProFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = colorA6A6A6
                    )
                }) {
            }
        }
        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else if (uiState.error.isNotBlank()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    text = uiState.error,
                    textAlign = TextAlign.Center,
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        } else if (uiState.dataLoaded && uiState.locations.isEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    text = "No results 🙁",
                    textAlign = TextAlign.Center,
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        } else {
            item {
                Spacer(modifier = Modifier.height(18.dp))
            }
            items(count = uiState.locations.size, key = {
                uiState.locations[it].name
            }) {
                SearchListCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    location = uiState.locations[it]
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
        }

    }
}
