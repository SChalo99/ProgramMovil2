package pe.edu.ulima.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    var id: Int = 0,
    @SerializedName("number")
    var codigo: Int = 0,
    @SerializedName("name")
    var nombre: String = "",
    @SerializedName("weight")
    var peso: Float = 0F,
    @SerializedName("height")
    var talla: Float = 0F,
    @SerializedName("image_url")
    var url: String = "",
)