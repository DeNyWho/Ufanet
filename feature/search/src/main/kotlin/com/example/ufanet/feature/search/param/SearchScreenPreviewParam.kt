package com.example.ufanet.feature.search.param

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.example.ufanet.core.uikit.param.GlobalParams
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.state.StateListWrapper
import com.example.ufanet.feature.search.model.SearchEvent
import com.example.ufanet.feature.search.model.SearchState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal data class SearchScreenPreviewParam(
    val state: SearchState,
    val eventHandler: (SearchEvent) -> Unit = {},
    val searchResults: Flow<PagingData<Story>>,
)

internal class SearchScreenProvider: PreviewParameterProvider<SearchScreenPreviewParam> {
    override val count: Int
        get() = super.count
    override val values: Sequence<SearchScreenPreviewParam>
        get() = listOf(
            SearchScreenPreviewParam(
                state = SearchState(
                    stories = StateListWrapper(data = GlobalParams.StoryList.toImmutableList()),
                ),
                searchResults = flow { emit(PagingData.from(listOf())) },
            ),
            SearchScreenPreviewParam(
                state = SearchState(
                    query = "Уфимский",
                ),
                searchResults = flow {
                    emit(
                        PagingData.from(
                            data = GlobalParams.StoryList,
                            sourceLoadStates = LoadStates(
                                refresh = LoadState.NotLoading(false),
                                append = LoadState.NotLoading(false),
                                prepend = LoadState.NotLoading(false),
                            )
                        )
                    )
                },
            ),
        ).asSequence()
}