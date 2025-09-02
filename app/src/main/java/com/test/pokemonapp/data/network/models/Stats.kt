package com.test.pokemonapp.data.network.models

import com.google.gson.annotations.SerializedName

enum class Stats {
    @SerializedName("name") NAME,
    @SerializedName("hp") HP,
    @SerializedName("attack") ATTACK,
    @SerializedName("defense") DEFENSE,
    @SerializedName("special-attack") SP_ATTACK,
    @SerializedName("special-defense") SP_DEFENSE,
    @SerializedName("speed") SPEED,
}