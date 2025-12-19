package com.example.ufanet.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.ufanet.core.uikit.components.textfield.UfanetTextField
import com.example.ufanet.core.uikit.theme.MediumEmphasis
import com.example.ufanet.core.uikit.theme.Primary
import com.example.ufanet.core.uikit.util.DefaultPreview
import com.example.ufanet.core.uikit.util.rememberLazyGridState
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.feature.search.component.card.SearchCard
import com.example.ufanet.feature.search.model.SearchEvent
import com.example.ufanet.feature.search.model.SearchState
import com.example.ufanet.feature.search.param.SearchScreenPreviewParam
import com.example.ufanet.feature.search.param.SearchScreenProvider
import kotlinx.coroutines.flow.Flow

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(initialValue = null)

    SearchUI(
        state = state,
        eventHandler = viewModel::handleEvent,
        searchResults = viewModel.searchResults,
    )
}

@Composable
private fun SearchUI(
    state: SearchState,
    searchResults: Flow<PagingData<Story>>,
    eventHandler: (SearchEvent) -> Unit,
) {
    val items = searchResults.collectAsLazyPagingItems()
    val gridState = items.rememberLazyGridState()

    Column(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        UfanetTextField(
            modifier = Modifier
                .height(54.dp)
                .fillMaxWidth(),
            query = state.query,
            onChangeQuery = { query ->
                eventHandler.invoke(SearchEvent.OnSearchQueryChanged(query))
            },
            placeHolder = stringResource(R.string.feature_search_text_field_placeholder),
        )
        if(items.loadState.append.endOfPaginationReached && items.itemCount == 0 && !state.isSearching) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.feature_search_empty_message),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                state = gridState,
                contentPadding = PaddingValues(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    count = items.itemCount,
                    key = items.itemKey { it.uniqueName },
                ) { index ->
                    val item = items[index]
                    item?.let {
                        SearchCard(
                            story = item,
                            onFavouriteClick = { uniqueName ->
                                eventHandler.invoke(SearchEvent.OnFavouriteClick(uniqueName))
                            },
                            onCardClick = { url ->
                                eventHandler.invoke(SearchEvent.OnSearchCardClick(url))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSearchScreen(
    @PreviewParameter(SearchScreenProvider::class) param: SearchScreenPreviewParam,
) {
    DefaultPreview(true) {
        SearchUI(
            state = param.state,
            eventHandler = param.eventHandler,
            searchResults = param.searchResults,
        )
    }
}