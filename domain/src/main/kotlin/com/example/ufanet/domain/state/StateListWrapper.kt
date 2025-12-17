package com.example.ufanet.domain.state

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class StateListWrapper<T>(
    val data: ImmutableList<T> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
) {
    companion object {
        inline fun <reified T> loading(): StateListWrapper<T> {
            return StateListWrapper(isLoading = true)
        }
    }
}