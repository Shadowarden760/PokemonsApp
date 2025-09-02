package com.test.pokemonapp.ui.features.main.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeightFilter(
    heightFilter: Int,
    onHeightChange: (Int) -> Unit,
) {
    Text(
        text = "Height",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(8.dp))
    Slider(
        value = heightFilter.toFloat(),
        onValueChange = { newValue ->
            onHeightChange(newValue.toInt())
        },
        valueRange = 0f..100f
    )
    Text(text = "Current height: $heightFilter")
    Spacer(modifier = Modifier.height(32.dp))
}