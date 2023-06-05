package pe.edu.ulima.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.models.beans.PokemonLocal

@Dao
interface PokemonDao {
    @Insert
    fun insertMany(pokemon:List<PokemonLocal>)

    @Query("Select * FROM pokemons")
    fun getPokemon(): List<PokemonLocal>

    @Query("DELETE FROM pokemons")
    fun deleteAllPokemons()
}