package com.vikanshu.search

import com.vikanshu.data.local.entity.Location

data class SearchUiState(
    var isLoading: Boolean,
    var dataLoaded: Boolean,
    var error: String,
    var locations: List<Location>
)