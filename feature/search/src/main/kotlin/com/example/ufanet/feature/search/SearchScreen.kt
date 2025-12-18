package com.example.ufanet.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ufanet.core.uikit.components.textfield.UfanetTextField
import com.example.ufanet.core.uikit.theme.MediumEmphasis
import com.example.ufanet.core.uikit.theme.Primary
import com.example.ufanet.feature.search.component.card.SearchCard
import com.example.ufanet.feature.search.model.SearchEvent
import com.example.ufanet.feature.search.model.SearchState

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(initialValue = null)

    SearchUI(
        state = state,
        eventHandler = viewModel::handleEvent,
    )
}

@Composable
private fun SearchUI(
    lazyGridState: LazyGridState = rememberLazyGridState(),
    state: SearchState,
    eventHandler: (SearchEvent) -> Unit,
) {
    Column(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        UfanetTextField(
            modifier = Modifier
                .height(54.dp)
                .fillMaxWidth(),
            query = state.query,
            onChangeQuery = {
                eventHandler.invoke(SearchEvent.OnSearchQueryChanged(it))
            },
            placeHolder = stringResource(R.string.feature_search_text_field_placeholder),
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            state = lazyGridState,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                state.stories.data,
                key = { it.url },
            ) { data ->
                SearchCard(
                    story = data,
                )
            }
        }
    }
}