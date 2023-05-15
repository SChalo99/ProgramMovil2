package pe.edu.ulima.ui.app.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.services.PokemonService

class PokemonDetailViewModel: ViewModel() {
    private val _id = MutableLiveData<Int>(0)
    var id: LiveData<Int> = _id
    fun updateId(it: Int){
        _id.postValue(it)
    }

    private val _titulo = MutableLiveData<String>("")
    var titulo: LiveData<String> = _titulo
    fun updateTitulo(it: String){
        _titulo.postValue(it)
    }

    private val _url = MutableLiveData<String>("")
    var url: LiveData<String> = _url
    fun updateUrl(it: String){
        _url.postValue(it)
    }

    private val _name = MutableLiveData<String>("")
    var name: LiveData<String> = _name
    fun updateName(it: String){
        _name.postValue(it)
    }

    private val _peso = MutableLiveData<Float>(0f)
    var peso: LiveData<Float> = _peso
    fun updatePeso(it: Float){
        _peso.postValue(it)
    }

    private val _talla = MutableLiveData<Float>(0f)
    var talla: LiveData<Float> = _talla
    fun updateTalla(it: Float){
        _talla.postValue(it)
    }

    fun getPokemon(id: Int){
        val pokemonsList: List<Pokemon> = PokemonService.fetchAll()
        for(pokemon in pokemonsList){
            if(pokemon.id == id){
                this.updateTitulo("Editar Pokemon")
                this.updatePeso(pokemon.peso)
                this.updateTalla(pokemon.talla)
                this.updateUrl(pokemon.url)
                this.updateName(pokemon.nombre)
            }
        }
    }

    fun unsetPokemon(){
        this.updateTitulo("Crear Pokemon")
        this.updatePeso(0f)
        this.updateTalla(0f)
        this.updateUrl("")
        this.updateName("")
    }

    private val _uri2 = MutableLiveData<Uri>(null)
    var uri2: LiveData<Uri> = _uri2
    fun updateUri2(it: Uri){
        _uri2.postValue(it)
    }

    private val _uri = mutableStateOf<Uri?>(null)
    val uri get() = _uri.value
    fun setUri(uri: Uri) {
        _uri.value = uri
    }
}