package com.example.ufanet.feature.search.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface SearchEvent {
    data object LoadInitialData: SearchEvent
    data class OnSearchCardClick(val url: String): SearchEvent
    data class OnSearchQueryChanged(val query: String): SearchEvent
    data class OnFavouriteClick(val uniqueName: String): SearchEvent
}