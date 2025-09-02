package com.test.pokemonapp.domain

data class PokemonData(
    val pokemonName: String,
    val pokemonWeight: Int,
    val pokemonHeight: Int,
    val pokemonPhoto: String?,
    val pokemonHp: Int,
    val pokemonAttack: Int,
    val pokemonDefense: Int,
    val pokemonSAttack: Int,
    val pokemonSDefense: Int,
    val pokemonSpeed: Int
)