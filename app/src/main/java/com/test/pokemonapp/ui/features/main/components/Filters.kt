package com.test.pokemonapp.ui.features.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.pokemonapp.R
import com.test.pokemonapp.data.network.models.Stats

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filters(
    onApplyFilters: (weight: Int, height: Int, newOrder: Stats) -> Unit,
    onDismiss: () -> Unit
) {
    val weightFilter = remember { mutableIntStateOf(0) }
    val heightFilter = remember { mutableIntStateOf(0) }
    val orderFilter = remember { mutableStateOf(Stats.NAME) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onDismiss) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            Text(
                text = "Filters",
                style = MaterialTheme.typography.headlineSmall
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        OrderFilter(
            orderFilter = orderFilter.value,
            onOrderChange = { newOrder -> orderFilter.value = newOrder }
        )
        WeightFilter(
            weightFilter = weightFilter.intValue,
            onWeightChange = { weightFilter.intValue = it }
        )
        HeightFilter(
            heightFilter = heightFilter.intValue,
            onHeightChange = { heightFilter.intValue = it }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = {
                    weightFilter.intValue = 0
                    heightFilter.intValue = 0
                    orderFilter.value = Stats.NAME
                }
            ) {
                Text("Reset")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    onApplyFilters(weightFilter.intValue, heightFilter.intValue, orderFilter.value)
                }
            ) {
                Text("Apply")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

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