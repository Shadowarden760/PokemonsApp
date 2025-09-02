package com.test.pokemonapp.ui.features.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.test.pokemonapp.domain.PokemonData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonList(pokemons: LazyPagingItems<PokemonData>) {
    if (pokemons.loadState.refresh is LoadState.Loading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            CircularProgressIndicator()
            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        Box {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(all = 16.dp)
            ) {
                items(pokemons.itemCount) {
                    pokemons[it]?.let { pokemonData ->
                        PokemonItem(pokemonData)
                    }
                }
            }
            if (pokemons.loadState.append is LoadState.Loading) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomEnd)
                ) {
                    CircularProgressIndicator()
                }
            }
            if (pokemons.loadState.append is LoadState.NotLoading && pokemons.itemCount == 0) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Pokemons not found.....")
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}