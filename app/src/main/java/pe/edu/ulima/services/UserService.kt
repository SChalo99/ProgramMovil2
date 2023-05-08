package pe.edu.ulima.services

import pe.edu.ulima.models.Usuario

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
}