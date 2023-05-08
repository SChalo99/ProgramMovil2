package pe.edu.ulima.ui.app.uis

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel
import pe.edu.ulima.ui.app.viewmodels.ProfileViewModel

@Preview
@Composable
public fun ProfileScreenPreview(){
    PokemonScreen(
        PokemonViewModel(),
        rememberNavController()
    )
}

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
){
    // viewmodel
    val nombre: String by viewModel.nombre.observeAsState("")
    val url: String by viewModel.imagen.observeAsState("")
    val correo: String by viewModel.correo.observeAsState("")
    val usuario: String by viewModel.usuario.observeAsState("")

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text ="Perfil del Usuario",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = rememberImagePainter(data = url),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 10.dp),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                TextField(
                    value = nombre,
                    onValueChange = {
                        viewModel.updateNombre(it)
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Nombre")
                    },
                    placeholder = {
                        Text(text = "")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    value = usuario,
                    onValueChange = {
                        viewModel.updateUsuario(it)
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Usuario")
                    },
                    placeholder = {
                        Text(text = "")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    value = correo,
                    onValueChange = {
                        viewModel.updateCorreo(it)
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Correo")
                    },
                    placeholder = {
                        Text(text = "")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
            }
        }
    }
}