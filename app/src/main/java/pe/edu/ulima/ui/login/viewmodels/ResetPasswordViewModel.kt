package pe.edu.ulima.ui.login.viewmodels

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class ResetPasswordScreenViewModel: ViewModel() {
    private val _correo = MutableLiveData<String>("")
    var correo: LiveData<String> = _correo
    fun updateCorreo(it: String){
        _correo.postValue(it)
    }

    private val _mensaje = MutableLiveData<String>("")
    var mensaje: LiveData<String> = _mensaje
    fun updateMensaje(it: String){
        _mensaje.postValue(it)
    }

    fun reset(){
        val pattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
        val email = correo.value
        val matcher: Matcher = pattern.matcher(email)
        val matchFound: Boolean = matcher.matches()
        if (matchFound){
            updateMensaje("Se ha enviado la recuperación de contraseña a su correo")
        }else{
            updateMensaje("Error: Correo inválido")
        }
    }
}