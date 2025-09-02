package com.test.pokemonapp.domain

import com.test.pokemonapp.data.network.models.Stats

data class FilterData(
    val query: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val sortBy: Stats? = null
)