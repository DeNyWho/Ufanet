package com.example.ufanet.data.network.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultResponseDTO<T>(
    @SerialName("error")
    val error: String,
    @SerialName("detail")
    val detail: T,
    @SerialName("status")
    val status: String,
    @SerialName("status_id")
    val statusId: Int,
)