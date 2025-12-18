package com.example.ufanet.data.source.mapper.stories

import com.example.ufanet.data.local.model.stories.StoryEntity
import com.example.ufanet.data.network.model.dto.stories.StoryDTO
import com.example.ufanet.domain.model.story.Story

internal fun StoryDTO.toStories(): Story {
    return Story(
        imageLogo = imageLogo,
        newsName = newsName,
        url = url,
        uniqueName = uniqueName,
    )
}

internal fun StoryDTO.toEntity(): StoryEntity {
    return StoryEntity(
        imageLogo = imageLogo,
        newsName = newsName,
        url = url,
        uniqueName = uniqueName,
    )
}

internal fun StoryEntity.toStory(): Story {
    return Story(
        imageLogo = imageLogo,
        newsName = newsName,
        url = url,
        uniqueName = uniqueName,
        isFavourite = isFavourite,
    )
}