package com.example.ufanet.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet.core.common.util.deeplink.DeepLink
import com.example.ufanet.domain.usecase.stories.ChangeFavouriteStoriesStatusUseCase
import com.example.ufanet.domain.usecase.stories.GetStoriesUseCase
import com.example.ufanet.feature.search.model.SearchAction
import com.example.ufanet.feature.search.model.SearchEvent
import com.example.ufanet.feature.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val changeFavouriteStoriesStatusUseCase: ChangeFavouriteStoriesStatusUseCase,
    private val deepLink: DeepLink,
): ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val _action = MutableSharedFlow<SearchAction>()
    val action: SharedFlow<SearchAction> = _action.asSharedFlow()

    init {
        _state
            .map { it.query }
            .distinctUntilChanged()
            .debounce(500)
            .flatMapLatest { queryString ->
                getStoriesUseCase.invoke(queryString.trim().takeIf { it.isNotEmpty() })
            }
            .onEach { stories ->
                _state.update { current ->
                    current.copy(
                        stories = stories,
                        isSearching = false,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun handleEvent(intent: SearchEvent) {
        when(intent) {
            is SearchEvent.OnSearchQueryChanged -> updateQuery(intent.query)
            is SearchEvent.OnFavouriteClick -> changeFavourite(intent.uniqueName)
            is SearchEvent.OnSearchCardClick -> openWeb(intent.url)
        }
    }

    private fun updateQuery(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    query = query,
                    isSearching = query.isNotEmpty(),
                )
            }
        }
    }

    private fun changeFavourite(uniqueName: String) {
        viewModelScope.launch {
            changeFavouriteStoriesStatusUseCase.invoke(uniqueName)
        }
    }

    private fun openWeb(url: String) {
        deepLink.openWeb(url)
    }
}