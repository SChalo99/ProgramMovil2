package pe.edu.ulima.models

data class Usuario (
    var id: Int = 0,
    var usuario: String = "",
    var contrasenia: String = "",
    var nombre: String = "",
    var correo: String = "",
    var imagen: String = "",
)