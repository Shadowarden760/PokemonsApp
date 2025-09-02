package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class StatDTO (
    @SerializedName("name") val name: Stats,
    @SerializedName("url") val url: String,
)