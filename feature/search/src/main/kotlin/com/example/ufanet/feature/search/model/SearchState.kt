package com.example.ufanet.feature.search.model

import androidx.compose.runtime.Immutable
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.state.StateListWrapper

@Immutable
internal data class SearchState(
    val query: String = "",
    val isSearching: Boolean = false,
    val isSearchBarFocused: Boolean = false,
    val stories: StateListWrapper<Story> = StateListWrapper.loading(),
)