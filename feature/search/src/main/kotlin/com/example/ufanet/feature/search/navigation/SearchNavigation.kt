package com.example.ufanet.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ufanet.feature.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable data object SearchRoute

fun NavGraphBuilder.searchScreen() {
    composable<SearchRoute> {
        SearchScreen()
    }
}