package com.test.pokemonapp.ui.features.main.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.test.pokemonapp.domain.PokemonData

@Composable
fun PokemonItem(pokemonDBO: PokemonData) {
    val context = LocalContext.current
    Card(
        onClick = {
            Toast.makeText(context,
                "${pokemonDBO.pokemonName} " +
                        "W:${pokemonDBO.pokemonWeight} H:${pokemonDBO.pokemonHeight}\n" +
                        "HP:${pokemonDBO.pokemonHp} SP:${pokemonDBO.pokemonSpeed} " +
                        "AT:${pokemonDBO.pokemonAttack} DF:${pokemonDBO.pokemonDefense} " +
                        "SAT:${pokemonDBO.pokemonSAttack} SDF:${pokemonDBO.pokemonSDefense} ",
                Toast.LENGTH_LONG).show()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonDBO.pokemonPhoto)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(100.dp),
            )
            Text(
                text = pokemonDBO.pokemonName.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}