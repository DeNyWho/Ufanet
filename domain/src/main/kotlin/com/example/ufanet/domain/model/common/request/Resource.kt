package com.example.ufanet.domain.model.common.request

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T) : Resource<T>()

    data class Error(
        val error: String,
    ) : Resource<Nothing>()
}