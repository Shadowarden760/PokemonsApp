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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.test.pokemonapp.R
import com.test.pokemonapp.data.network.models.Stats

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filters(
    weightFilter: Int,
    heightFilter: Int,
    orderFilter: Stats?,
    onWeightChange: (Int) -> Unit,
    onHeightChange: (Int) -> Unit,
    onOrderChange: (Stats?) -> Unit,
    onApplyFilters: () -> Unit,
    onDismiss: () -> Unit
) {
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
                text = stringResource(R.string.filters_text_filters),
                style = MaterialTheme.typography.headlineSmall
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        OrderFilter(
            orderFilter = orderFilter,
            onOrderChange = onOrderChange
        )
        WeightFilter(
            weightFilter = weightFilter,
            onWeightChange = onWeightChange
        )
        HeightFilter(
            heightFilter = heightFilter,
            onHeightChange = onHeightChange
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = {
                    onWeightChange(0)
                    onHeightChange(0)
                    onOrderChange(null)
                }
            ) {
                Text(stringResource(R.string.filters_button_reset))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onApplyFilters) {
                Text(stringResource(R.string.filters_button_apply))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}