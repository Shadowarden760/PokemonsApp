package com.test.pokemonapp.data

import com.test.pokemonapp.common.Constants.PAGE_SIZE
import com.test.pokemonapp.data.database.models.PokemonDBO
import com.test.pokemonapp.data.network.models.PokemonDataDTO
import com.test.pokemonapp.data.network.models.Stats

fun PokemonDataDTO.toPokemonDBO(): PokemonDBO {
    return PokemonDBO(
        pokemonId = this.pokemonId,
        pokemonName = this.pokemonName,
        pokemonWeight = this.pokemonWeight,
        pokemonHeight = this.pokemonHeight,
        pokemonPhoto = this.pokemonPhotos.frontDefault ?: this.pokemonPhotos.frontFemale,
        pokemonHp = this.pokemonStats.find { it.stat.name == Stats.HP }?.baseStat ?: 0,
        pokemonAttack = this.pokemonStats.find { it.stat.name == Stats.ATTACK }?.baseStat ?: 0,
        pokemonDefense = this.pokemonStats.find { it.stat.name == Stats.DEFENSE }?.baseStat ?: 0,
        pokemonSAttack = this.pokemonStats.find { it.stat.name == Stats.SP_ATTACK }?.baseStat ?: 0,
        pokemonSDefense = this.pokemonStats.find { it.stat.name == Stats.SP_DEFENSE }?.baseStat ?: 0,
        pokemonSpeed = this.pokemonStats.find { it.stat.name == Stats.SPEED }?.baseStat ?: 0,
        pokemonPage = ((this.pokemonId - 1) / PAGE_SIZE) + 1,

    )
}