package com.test.pokemonapp.ui.features.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.pokemonapp.data.network.models.Stats

@Composable
fun OrderFilter(
    orderFilter: Stats,
    onOrderChange: (newOrder: Stats) -> Unit
) {
    val options = listOf(
        Stats.NAME, Stats.HP, Stats.ATTACK, Stats.DEFENSE,
        Stats.SP_ATTACK, Stats.SP_DEFENSE, Stats.SPEED
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Order By...",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index,  option ->
                SegmentedButton(
                    selected = orderFilter == option,
                    onClick = { onOrderChange(option) },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    modifier = Modifier.size(70.dp)
                ) {
                    Text(
                        text = if (option.name.length >= 5) option.name.substring(0, 4) else option.name,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}