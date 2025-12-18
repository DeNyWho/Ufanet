package com.example.ufanet.core.uikit.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ufanet.core.uikit.theme.UfanetTheme

@Composable
fun DefaultPreview(needBackground: Boolean = false, content: @Composable (ColumnScope.() -> Unit)) {
    UfanetTheme {
        Column(
            if(needBackground) Modifier.background(MaterialTheme.colorScheme.background) else Modifier,
            content = content,
        )
    }
}