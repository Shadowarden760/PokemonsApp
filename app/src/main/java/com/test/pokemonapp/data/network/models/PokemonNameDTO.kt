package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonNameDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)