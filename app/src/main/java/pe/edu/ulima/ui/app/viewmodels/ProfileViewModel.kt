package pe.edu.ulima.ui.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.services.UserService

class ProfileViewModel: ViewModel() {
    private val _id = MutableLiveData<Int>(0)
    var id: LiveData<Int> = _id
    fun updateId(it: Int){
        _id.postValue(it)
    }

    private val _usuario = MutableLiveData<String>("")
    var usuario: LiveData<String> = _usuario
    fun updateUsuario(it: String){
        _usuario.postValue(it)
    }

    private val _contrasenia = MutableLiveData<String>("")
    var contrasenia: LiveData<String> = _contrasenia
    fun updateContrasenia(it: String){
        _contrasenia.postValue(it)
    }

    private val _nombre = MutableLiveData<String>("")
    var nombre: LiveData<String> = _nombre
    fun updateNombre(it: String){
        _nombre.postValue(it)
    }

    private val _correo = MutableLiveData<String>("")
    var correo: LiveData<String> = _correo
    fun updateCorreo(it: String){
        _correo.postValue(it)
    }

    private val _imagen = MutableLiveData<String>("")
    var imagen: LiveData<String> = _imagen
    fun updateImagen(it: String){
        _imagen.postValue(it)
    }

    fun setUsuario(id: Int){
        val usuario = UserService.fetchOne(id)
        this.updateUsuario(usuario.usuario)
        this.updateCorreo(usuario.correo)
        this.updateNombre(usuario.nombre)
        this.updateImagen(usuario.imagen)
    }
}