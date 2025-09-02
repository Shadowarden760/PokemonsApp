package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DreamWorldDTO(
    @SerializedName("front_default")val frontDefault: String?,
    @SerializedName("front_female")val frontFemale: String?,
)