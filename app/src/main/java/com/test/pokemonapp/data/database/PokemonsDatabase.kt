package com.test.pokemonapp.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.pokemonapp.data.database.converters.ListTypeConverter
import com.test.pokemonapp.data.database.dao.PokemonsDao
import com.test.pokemonapp.data.database.models.PokemonDBO

@Database(
    entities = [PokemonDBO::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
    ],
    exportSchema = true,
    version = 4
)
@TypeConverters(ListTypeConverter::class)
abstract class PokemonsDatabase(): RoomDatabase() {

    abstract fun pokemonsDao(): PokemonsDao
}

fun createPokemonDatabase(appContext: Context): PokemonsDatabase {
    return Room.databaseBuilder(
        context = appContext,
        klass = PokemonsDatabase::class.java,
        name = "pokemons_db"
    ).build()
}

