package com.test.pokemonapp.data.network

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.test.pokemonapp.data.network.models.AllPokemonsDTO
import com.test.pokemonapp.data.network.models.PokemonDataDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun requestPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Result<AllPokemonsDTO>

    @GET("/api/v2/pokemon/{pokemonName}")
    suspend fun requestPokemonData(
        @Path("pokemonName") pokemonName: String
    ): Result<PokemonDataDTO>
}

fun createPokemonApi(baseUrl: String): PokemonApi {
    val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        )
        .callTimeout(15_000L, TimeUnit.MILLISECONDS)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(client)
        .build()
    return retrofit.create()
}