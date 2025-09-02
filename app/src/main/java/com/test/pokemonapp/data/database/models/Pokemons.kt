package com.test.pokemonapp.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemons")
data class PokemonDBO(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("pokemon_id") val pokemonId: Int,
    @ColumnInfo("pokemon_name") val pokemonName: String,
    @ColumnInfo("pokemon_weight", defaultValue = "0") val pokemonWeight: Int,
    @ColumnInfo("pokemon_height", defaultValue = "0") val pokemonHeight: Int,
    @ColumnInfo("pokemon_photo") val pokemonPhoto: String?,
    @ColumnInfo("pokemon_hp", defaultValue = "0") val pokemonHp: Int,
    @ColumnInfo("pokemon_attack", defaultValue = "0") val pokemonAttack: Int,
    @ColumnInfo("pokemon_defense", defaultValue = "0") val pokemonDefense: Int,
    @ColumnInfo("pokemon_s_attack", defaultValue = "0") val pokemonSAttack: Int,
    @ColumnInfo("pokemon_s_defense", defaultValue = "0") val pokemonSDefense: Int,
    @ColumnInfo("pokemon_speed", defaultValue = "0") val pokemonSpeed: Int,
    @ColumnInfo("pokemon_page", defaultValue = "0") val pokemonPage: Int,
)