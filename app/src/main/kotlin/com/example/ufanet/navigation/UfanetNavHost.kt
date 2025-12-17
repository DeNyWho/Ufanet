package com.example.ufanet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ufanet.feature.search.navigation.SearchRoute
import com.example.ufanet.feature.search.navigation.searchScreen

@Composable
fun UfanetNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SearchRoute,
    ) {
        searchScreen()
    }
}