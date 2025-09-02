package com.test.pokemonapp.ui.features.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.pokemonapp.R

@Composable
fun Header(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onFiltersClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.pokemons_logo),
            contentDescription = null
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Search(
                query = searchQuery,
                onQueryChange = onSearchQueryChange
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick =  onFiltersClick,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp),
                    contentDescription = null
                )
            }
        }
    }

}

@Preview
@Composable
private fun HeaderPreview() {
    Header("", {}, {})
}