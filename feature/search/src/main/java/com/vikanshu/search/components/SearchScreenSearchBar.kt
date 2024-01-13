package com.vikanshu.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.colorA6A6A6
import com.vikanshu.core_ui.ui.colorE8E8E8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenSearchBar(
    query: String,
    isLoading: Boolean,
    onSearchQueryChanged: (String) -> Unit
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        enabled = !isLoading,
        query = query,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "",
                tint = if (query.length > 3) Color.Black else colorA6A6A6
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = colorE8E8E8,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = colorA6A6A6,
                unfocusedPlaceholderColor = colorA6A6A6,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            )
        ),
        onQueryChange = onSearchQueryChanged,
        onSearch = onSearchQueryChanged,
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(
                text = "Enter City, Region or Place",
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Medium,
                color = colorA6A6A6
            )
        }) {}
}