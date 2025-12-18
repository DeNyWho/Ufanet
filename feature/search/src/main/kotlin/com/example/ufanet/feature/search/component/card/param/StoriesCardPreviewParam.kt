package com.example.ufanet.feature.search.component.card.param

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.ufanet.core.uikit.param.GlobalParams
import com.example.ufanet.domain.model.story.Story

internal data class StoriesCardPreviewParam(
    val story: Story,
    val onFavouriteClick: (String) -> Unit = {},
)

internal class StoriesCardProvider: PreviewParameterProvider<StoriesCardPreviewParam> {
    override val count: Int
        get() = super.count
    override val values: Sequence<StoriesCardPreviewParam>
        get() = listOf(
            StoriesCardPreviewParam(
                story = GlobalParams.Story,
            ),
            StoriesCardPreviewParam(
                story = GlobalParams.Story.copy(isFavourite = true),
            ),
        ).asSequence()
}