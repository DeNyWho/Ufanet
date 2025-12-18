package com.example.ufanet.data.source.mapper.stories

import com.example.ufanet.data.network.model.dto.stories.StoriesDTO
import com.example.ufanet.domain.model.story.Story

internal fun StoriesDTO.toStories(): Story {
    return Story(
        imageLogo = imageLogo,
        newsName = newsName,
        url = url,
    )
}