package com.example.ufanet.data.network.model.dto.stories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoriesResponseDTO(
    @SerialName("stories")
    val stories: List<StoriesDTO>,
)