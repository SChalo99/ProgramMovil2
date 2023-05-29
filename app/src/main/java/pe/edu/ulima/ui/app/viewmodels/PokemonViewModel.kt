package pe.edu.ulima.ui.app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pe.edu.ulima.configs.BackendClient
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.services.PokemonService
import kotlin.concurrent.thread

class PokemonViewModel: ViewModel(){
    private val _pokemons = mutableStateOf<List<Pokemon>?>(
        listOf()
    )
    val pokemons get() = _pokemons.value
    fun setPokemons() {
        val apiService = BackendClient.buildService(PokemonService::class.java)
        thread {
            try {
                val response = apiService.fetchAll("", "").execute()
                if (response.isSuccessful) {
                    println(response.body())
                    _pokemons.value = response.body()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val _selectedId = mutableStateOf<Int?>(
        null
    )
    val selectedId get() = _selectedId.value
    fun setSelectedId(selectedId: Int) {
        _selectedId.value = selectedId
    }
}