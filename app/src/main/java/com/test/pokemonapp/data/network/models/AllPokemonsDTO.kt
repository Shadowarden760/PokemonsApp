package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AllPokemonsDTO(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val nextUrl: String?,
    @SerializedName("previous") val previousUrl: String?,
    @SerializedName("results") val results: List<PokemonNameDTO>
)