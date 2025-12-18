package com.example.ufanet.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet.domain.usecase.stories.ChangeFavouriteStoriesStatusUseCase
import com.example.ufanet.domain.usecase.stories.GetStoriesUseCase
import com.example.ufanet.feature.search.model.SearchAction
import com.example.ufanet.feature.search.model.SearchEvent
import com.example.ufanet.feature.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val changeFavouriteStoriesStatusUseCase: ChangeFavouriteStoriesStatusUseCase,
): ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val _action = MutableSharedFlow<SearchAction>()
    val action: SharedFlow<SearchAction> = _action.asSharedFlow()

    init {
        handleEvent(SearchEvent.LoadInitialData)
    }

    fun handleEvent(intent: SearchEvent) {
        when(intent) {
            SearchEvent.LoadInitialData -> loadInitialData()
            is SearchEvent.OnSearchQueryChanged -> updateQuery(intent.query)
            is SearchEvent.OnFavouriteClick -> changeFavourite(intent.uniqueName)
            is SearchEvent.OnSearchCardClick -> {

            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            loadInitialSearch()
        }
    }

    private fun loadInitialSearch() {
        getStoriesUseCase.invoke(null)
            .onEach { result ->
                _state.update {
                    it.copy(
                        stories = result
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun updateQuery(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    query = query,
                )
            }

            loadSearch(query)
        }
    }

    private fun loadSearch(query: String) {
        getStoriesUseCase.invoke(query)
            .onEach { result ->
                _state.update {
                    it.copy(
                        stories = result
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun changeFavourite(uniqueName: String) {
        viewModelScope.launch {
            changeFavouriteStoriesStatusUseCase.invoke(uniqueName)
        }
    }
}