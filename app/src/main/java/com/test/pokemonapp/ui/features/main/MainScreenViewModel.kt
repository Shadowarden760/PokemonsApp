package com.test.pokemonapp.ui.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.test.pokemonapp.common.Constants.PAGE_SIZE
import com.test.pokemonapp.data.PokemonsRemoteMediator
import com.test.pokemonapp.data.database.dao.PokemonsDao
import com.test.pokemonapp.data.network.models.Stats
import com.test.pokemonapp.domain.FilterData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class, FlowPreview::class)
class MainScreenViewModel(
    remoteMediator: PokemonsRemoteMediator,
    databaseDao: PokemonsDao
): ViewModel() {
    private val _filterState = MutableStateFlow(FilterData())
    val filterState
        get() = _filterState.asStateFlow()

    fun updateFilters(
        query: String? = null,
        weight: Int? = null,
        height: Int? = null,
        sortBy: Stats? = null
    ) {
        _filterState.value = FilterData(
            query = query ?: _filterState.value.query,
            weight = weight ?: _filterState.value.weight,
            height = height ?: _filterState.value.height,
            sortBy = sortBy ?: _filterState.value.sortBy
        )
    }

    val pokemonsPagingFlow = _filterState
        .flatMapLatest { newFilters ->
            Pager(
                config = PagingConfig(pageSize = PAGE_SIZE),
                remoteMediator = remoteMediator,
                pagingSourceFactory = {
                    if (_filterState.value == FilterData()) {
                        databaseDao.getAllPokemons()
                    } else {
                        databaseDao.getAllPokemons(filters = newFilters)
                    }
                }
            ).flow.cachedIn(viewModelScope)
        }.cachedIn(viewModelScope)
}