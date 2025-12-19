package com.example.ufanet.feature.search.param

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.ufanet.core.uikit.param.GlobalParams
import com.example.ufanet.domain.state.StateListWrapper
import com.example.ufanet.feature.search.model.SearchEvent
import com.example.ufanet.feature.search.model.SearchState
import kotlinx.collections.immutable.toImmutableList

internal data class SearchScreenPreviewParam(
    val state: SearchState,
    val eventHandler: (SearchEvent) -> Unit = {},
)

internal class SearchScreenProvider: PreviewParameterProvider<SearchScreenPreviewParam> {
    override val count: Int
        get() = super.count
    override val values: Sequence<SearchScreenPreviewParam>
        get() = listOf(
            SearchScreenPreviewParam(
                state = SearchState(
                    stories = StateListWrapper(data = GlobalParams.StoryList.toImmutableList())
                )
            ),
            SearchScreenPreviewParam(
                state = SearchState(
                    query = "Уфимский",
                )
            ),
        ).asSequence()
}