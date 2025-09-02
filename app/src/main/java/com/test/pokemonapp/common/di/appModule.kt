package com.test.pokemonapp.common.di

import androidx.paging.ExperimentalPagingApi
import com.test.pokemonapp.data.PokemonsRemoteMediator
import com.test.pokemonapp.data.database.PokemonsDatabase
import com.test.pokemonapp.data.database.createPokemonDatabase
import com.test.pokemonapp.data.network.createPokemonApi
import com.test.pokemonapp.ui.features.main.MainScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val appModule = module {
    single { createPokemonApi(baseUrl = "https://pokeapi.co/") }
    single { createPokemonDatabase(appContext = androidContext()) }

    single { PokemonsRemoteMediator(api = get(), database = get()) }

    viewModel {
        val pokemonsDatabase: PokemonsDatabase = get()
        MainScreenViewModel(
            remoteMediator = get(),
            databaseDao = pokemonsDatabase.pokemonsDao()
        )
    }
}