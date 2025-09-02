package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDataDTO(
    @SerializedName("id") val pokemonId: Int,
    @SerializedName("name") val pokemonName: String,
    @SerializedName("weight") val pokemonWeight: Int,
    @SerializedName("height") val pokemonHeight: Int,
    @SerializedName("stats") val pokemonStats: List<PokemonStatsDTO>,
    @SerializedName("sprites") val pokemonPhotos: PokemonPhotoSetsDTO,
)