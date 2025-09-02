package com.test.pokemonapp.ui.features.main.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.test.pokemonapp.R

@Composable
fun WeightFilter(
    weightFilter: Int,
    onWeightChange: (Int) -> Unit,
) {
    Text(
        text = stringResource(R.string.filters_text_weight),
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
    Text(text = stringResource(R.string.filters_text_current_weight, weightFilter))
    Spacer(modifier = Modifier.height(24.dp))
}