package com.example.ufanet.data.source.mapper.stories

import com.example.ufanet.data.network.model.dto.stories.StoriesDTO
import com.example.ufanet.domain.model.stories.Stories

internal fun StoriesDTO.toStories(): Stories {
    return Stories(
        imageLogo = imageLogo,
        newsName = newsName,
        url = url,
    )
}