package com.example.ufanet.data.network.model.dto.stories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoriesDTO(
    @SerialName("image_logo")
    val imageLogo: String,
    @SerialName("news_name")
    val newsName: String,
    @SerialName("url")
    val url: String,
)