package com.example.ufanet.core.uikit.components.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ufanet.core.uikit.theme.MediumEmphasis
import com.example.ufanet.core.uikit.util.DefaultPreview

@Composable
fun UfanetTextField(
    modifier: Modifier = Modifier,
    query: String,
    onChangeQuery: (String) -> Unit,
    placeHolder: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier,
        maxLines = 1,
        interactionSource = interactionSource,
        singleLine = true,
        value = query,
        textStyle = MaterialTheme.typography.bodyLarge,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        onValueChange = {
            onChangeQuery.invoke(it)
        },
        label = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MediumEmphasis,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MediumEmphasis,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedPlaceholderColor = MediumEmphasis,
            unfocusedPlaceholderColor = MediumEmphasis,

        ),
    )
}

@Preview
@Composable
private fun PreviewUfanetTextField() {
    DefaultPreview(true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            UfanetTextField(
                query = "",
                onChangeQuery = {},
                placeHolder = "Поиск",
            )
            UfanetTextField(
                query = "Кастрюля упала на машину",
                onChangeQuery = {},
                placeHolder = "Поиск",
            )
        }
    }
}