package com.example.ufanet.feature.search.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface SearchAction {
    data class ShowError(val message: String): SearchAction
}