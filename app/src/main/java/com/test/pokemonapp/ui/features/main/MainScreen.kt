package com.test.pokemonapp.ui.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.pokemonapp.data.network.models.Stats
import com.test.pokemonapp.ui.features.main.components.Filters
import com.test.pokemonapp.ui.features.main.components.Header
import com.test.pokemonapp.ui.features.main.components.PokemonList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainScreenViewModel = koinViewModel()) {
    val pokemons = viewModel.pokemonsPagingFlow.collectAsLazyPagingItems()
    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val refreshState = rememberPullToRefreshState()
    val scope = rememberCoroutineScope()
    val isRefreshing = remember { mutableStateOf(false) }

    val filters = viewModel.filterState.collectAsStateWithLifecycle()
    val weightFilter = remember { mutableIntStateOf(0) }
    val heightFilter = remember { mutableIntStateOf(0) }
    val orderFilter = remember { mutableStateOf<Stats?>(null) }

    Column {
        Header(
            searchQuery = filters.value.query,
            onSearchQueryChange = { viewModel.updateFilters(query = it) },
            onFiltersClick = { showBottomSheet.value = true }
        )
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = isRefreshing.value,
            onRefresh = {
                scope.launch {
                    isRefreshing.value = true
                    pokemons.refresh()
                    delay(1000) // ¯\_(ツ)_/¯ animation moment of PullToRefresh
                    isRefreshing.value = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            PokemonList(pokemons = pokemons)
        }
    }

    if (showBottomSheet.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet.value = false }
        ) {
            Filters(
                weightFilter = weightFilter.intValue,
                heightFilter = heightFilter.intValue,
                orderFilter = orderFilter.value,
                onWeightChange = { weightFilter.intValue = it },
                onHeightChange = { heightFilter.intValue = it },
                onOrderChange = { orderFilter.value = it },
                onApplyFilters = {
                    viewModel.updateFilters(
                        weight = weightFilter.intValue,
                        height = heightFilter.intValue,
                        sortBy = orderFilter.value
                    )
                    showBottomSheet.value = false
                },
                onDismiss = { showBottomSheet.value = false }
            )
        }
    }
}
