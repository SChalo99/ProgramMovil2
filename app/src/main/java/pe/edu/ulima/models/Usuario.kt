package pe.edu.ulima.models

import com.google.gson.annotations.SerializedName

data class Usuario (
    val id: Int,
    @SerializedName("user")
    val usuario: String,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("password")
    val contrasenia: String,
    @SerializedName("email")
    val correo: String,
    @SerializedName("image_url")
    var imagen: String = "",
)