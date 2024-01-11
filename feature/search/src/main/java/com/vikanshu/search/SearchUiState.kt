package com.vikanshu.search

import com.vikanshu.data.local.entity.Location

data class SearchUiState(
    var isLoading: Boolean,
    var message: String,
    var locations: List<Location>
)