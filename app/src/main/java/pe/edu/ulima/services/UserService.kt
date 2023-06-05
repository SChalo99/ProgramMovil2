package pe.edu.ulima.services

import pe.edu.ulima.models.Usuario
import pe.edu.ulima.models.demo.UserValidate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/*
class UserService {
    companion object {
        val usuarios = listOf(
            Usuario (1, "admin","123", "Super Administrador", "root@ulima.edu.pe", "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/1.png"),
            Usuario (2, "pepe","123", "Pepe Valdivia", "pepe@ulima.edu.pe", "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/2.png"),
            Usuario (3, "sila","123", "Sila Esculapia", "sila@ulima.edu.pe", "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png")
        )

        fun validate(usuario: String, contrasenia: String): Int {
            var id = 0
            for(u in usuarios){
                if(u.usuario == usuario && u.contrasenia == contrasenia){
                    id = u.id
                }
            }
            return id
        }

        fun fetchOne(id: Int): Usuario{
            var usuario = Usuario()
            for(u in usuarios){
                if(u.id == id){
                    usuario = u
                }
            }
            return usuario
        }
    }
}*/

interface UserService{
    @POST("/user/validate")
    fun validate(
        @Body requestModel: UserValidate
    ): Call<Int>

    @GET("/user/fetch_one")
    fun fetchOne(
        @Query("id") id: Int
    ): Call<Usuario>
}
