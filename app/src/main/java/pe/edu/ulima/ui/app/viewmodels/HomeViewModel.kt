package pe.edu.ulima.ui.app.viewmodels

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pe.edu.ulima.configs.BackendClient
import pe.edu.ulima.configs.LocalDB
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.models.beans.PokemonLocal
import pe.edu.ulima.services.PokemonService
import kotlin.concurrent.thread

class HomeViewModel: ViewModel() {
    private val _pokemons = mutableStateOf<List<PokemonLocal>?>(
        listOf()
    )
    val pokemons get() = _pokemons.value
    fun setPokemons(activity: Activity) {
        val apiService = BackendClient.buildService(PokemonService::class.java)
        thread {
            val database = LocalDB.getDatabase(activity as Context)
            val pokemonDao = database.pokemonDao()
            _pokemons.value = pokemonDao.getPokemon()
        }

        if(_pokemons.value!!.isNullOrEmpty()){
        thread {
            try {
                val response = apiService.fetchAllLocal("", "").execute()
                if(response.isSuccessful){
                    println(response.body())
                    val database = LocalDB.getDatabase(activity as Context)
                    val pokemonDao = database.pokemonDao()
                    pokemonDao.insertMany(response.body()!!)
                    _pokemons.value = pokemonDao.getPokemon()
                }
            }catch (e: Exception){
                e.printStackTrace()
                activity.runOnUiThread{
                    Toast.makeText(
                        activity,
                        "Error HTTP: No se pudo traer el pokemon",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
         }
        }
    }
}