package com.test.pokemonapp.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.test.pokemonapp.data.database.models.PokemonDBO
import com.test.pokemonapp.data.network.models.Stats
import com.test.pokemonapp.domain.FilterData

@Dao
interface PokemonsDao {

    @Query("SELECT * FROM Pokemons")
    fun getAllPokemons(): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
    """)
    fun getAllPokemons(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_name ASC
    """)
    fun getAllPokemonsByName(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_hp DESC
    """)
    fun getAllPokemonsByHP(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_attack DESC
    """)
    fun getAllPokemonsByAttack(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_defense DESC
    """)
    fun getAllPokemonsByDefense(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_s_attack DESC
    """)
    fun getAllPokemonsBySAttack(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_s_defense DESC
    """)
    fun getAllPokemonsBySDefense(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    @Query("""
        SELECT * FROM Pokemons
        WHERE pokemon_name LIKE '%' || :query || '%'
        AND pokemon_weight >= :weight
        AND pokemon_height >= :height
        ORDER BY pokemon_speed DESC
    """)
    fun getAllPokemonsBySpeed(query: String, weight: Int, height: Int): PagingSource<Int, PokemonDBO>

    fun getAllPokemons(filters: FilterData): PagingSource<Int, PokemonDBO> {
        return when (filters.sortBy) {
            Stats.NAME -> getAllPokemonsByName(filters.query, filters.weight, filters.height)
            Stats.HP -> getAllPokemonsByHP(filters.query, filters.weight, filters.height)
            Stats.ATTACK -> getAllPokemonsByAttack(filters.query, filters.weight, filters.height)
            Stats.DEFENSE -> getAllPokemonsByDefense(filters.query, filters.weight, filters.height)
            Stats.SP_ATTACK -> getAllPokemonsBySAttack(filters.query, filters.weight, filters.height)
            Stats.SP_DEFENSE -> getAllPokemonsBySDefense(filters.query, filters.weight, filters.height)
            Stats.SPEED -> getAllPokemonsBySpeed(filters.query, filters.weight, filters.height)
            else -> getAllPokemons(filters.query, filters.weight, filters.height)
        }
    }

    @Upsert
    suspend fun upsertPokemons(pokemons: List<PokemonDBO>)

    @Delete
    suspend fun deletePokemons(pokemons: List<PokemonDBO>)

    @Query("DELETE FROM Pokemons")
    suspend fun clearAllPokemons()
}

//CASE WHEN :order = 2 THEN pokemon_hp END ASC,
//CASE WHEN :order = 3 THEN pokemon_attack END ASC,
//CASE WHEN :order = 4 THEN pokemon_defense END ASC,
//CASE WHEN :order = 5 THEN pokemon_s_attack END ASC,
//CASE WHEN :order = 6 THEN pokemon_s_defense END ASC,
//CASE WHEN :order = 7 THEN pokemon_speed END ASC