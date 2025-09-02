package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OtherDTO(
    @SerializedName("dream_world") val dreamWorld: DreamWorldDTO,
    @SerializedName("home") val home: HomeDTO,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkDTO,
    @SerializedName("showdown") val showdown: ShowdownDTO,
)