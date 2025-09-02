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
fun WeightFilter(
    weightFilter: Int,
    onWeightChange: (Int) -> Unit,
) {
    Text(
        text = "Weight",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(8.dp))
    Slider(
        value = weightFilter.toFloat(),
        onValueChange = { newValue ->
            onWeightChange(newValue.toInt())
        },
        valueRange = 0f..400f
    )
    Text(text = "Current weight: $weightFilter")
    Spacer(modifier = Modifier.height(24.dp))
}