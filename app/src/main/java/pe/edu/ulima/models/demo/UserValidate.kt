package pe.edu.ulima.models.demo

import com.google.gson.annotations.SerializedName

data class UserValidate(
    val user: String,
    @SerializedName("password")
    val contrasenia: String
)
