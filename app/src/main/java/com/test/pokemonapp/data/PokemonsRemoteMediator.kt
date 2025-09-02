package com.test.pokemonapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.test.pokemonapp.data.database.PokemonsDatabase
import com.test.pokemonapp.data.database.models.PokemonDBO
import com.test.pokemonapp.data.network.PokemonApi
import com.test.pokemonapp.data.network.models.PokemonDataDTO
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class PokemonsRemoteMediator(
    private val api: PokemonApi,
    private val database: PokemonsDatabase
): RemoteMediator<Int, PokemonDBO>() {
    private var lastLoadType: Int = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonDBO>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> {
                    lastLoadType = 1
                    lastLoadType
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        lastLoadType += 1
                        lastLoadType
                    } else {
                        if (lastLoadType <= lastItem.pokemonPage) {
                            lastLoadType = lastItem.pokemonPage + 1
                        } else {
                            lastLoadType += 1
                        }
                        lastLoadType
                    }
                }
            }
            val pokemonsRequest = api.requestPokemons(
                offset = (loadKey - 1) * state.config.pageSize,
                limit = state.config.pageSize
            )
            val pokemonsResult = pokemonsRequest.getOrNull()
            if (pokemonsResult != null) {
                val newPokemons = mutableListOf<PokemonDataDTO>()
                pokemonsResult.results.forEach { pokemonNameDTO ->
                    val pokemonDataRequest = api.requestPokemonData(pokemonName = pokemonNameDTO.name)
                    val pokemonData = pokemonDataRequest.getOrNull()
                    if (pokemonData != null) {
                        newPokemons.add(pokemonData)
                    }
                }
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.pokemonsDao().clearAllPokemons()
                    }
                    val pokemons = newPokemons.map { it.toPokemonDBO() }
                    database.pokemonsDao().upsertPokemons(pokemons)
                }
                MediatorResult.Success(newPokemons.isEmpty())
            } else {
                MediatorResult.Error(Throwable("No results from first request"))
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}