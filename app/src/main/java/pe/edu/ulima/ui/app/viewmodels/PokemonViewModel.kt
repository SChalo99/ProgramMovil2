package pe.edu.ulima.ui.app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.services.PokemonService

class PokemonViewModel: ViewModel(){
    private val _pokemons = mutableStateOf<List<Pokemon>?>(
        listOf()
    )
    val pokemons get() = _pokemons.value
    fun setPokemons() {
        _pokemons.value = PokemonService.fetchAll()
    }

    private val _selectedId = mutableStateOf<Int?>(
        null
    )
    val selectedId get() = _selectedId.value
    fun setSelectedId(selectedId: Int) {
        _selectedId.value = selectedId
    }
}