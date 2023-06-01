package pe.edu.ulima.ui.login.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.activities.AppActivity
import pe.edu.ulima.configs.BackendClient
import pe.edu.ulima.models.demo.UserValidate
import pe.edu.ulima.services.PokemonService
import pe.edu.ulima.services.UserService
import kotlin.concurrent.thread

class LoginViewModel: ViewModel() {
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

    private val _mensaje = MutableLiveData<String>("")
    var mensaje: LiveData<String> = _mensaje
    fun updateMensaje(it: String){
        _mensaje.postValue(it)
    }

    fun validar(context: Context){
        //val id: Int = UserService.validate(usuario.value!!, contrasenia.value!!)
        val apiService = BackendClient.buildService(UserService::class.java)
        var id = 0
        thread {
            try {
                val body = UserValidate(usuario.value!!, contrasenia.value!!)
                val response = apiService.validate(body).execute()
                if (response.isSuccessful) {
                    id = response.body()!!
                    if(id == 0){
                        updateMensaje("Error: Usuario y contraseña no válidos")
                    }else{
                        updateMensaje("Todo OK")
                            val appActivity =  Intent(context, AppActivity::class.java)
                            appActivity.putExtra("user_id", id)
                            context.startActivity(
                                appActivity
                            )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}