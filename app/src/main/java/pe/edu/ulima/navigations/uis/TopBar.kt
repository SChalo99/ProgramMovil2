package pe.edu.ulima.ui.theme

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.BuildConfig
import pe.edu.ulima.R
import pe.edu.ulima.configs.LocalDB
import kotlin.concurrent.thread

@Preview
@Composable
fun TopBarPreview(){
    TopBar(
        showBottomSheetDialog = {},
        rememberNavController(),
        0
    )
}

@Composable
fun TopBar(
    showBottomSheetDialog: () -> Unit,
    navController: NavHostController,
    id: Int
){
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ){
        TopAppBar(
            //modifier = Modifier.padding(bottom =  24.dp),
            //backgroundColor = Color(R.color.purple_500),
            elevation = 0.dp,
            title = {
                Text(
                    text = "Aplicación Demo",
                    color = Color.White
                )
            },
            actions = {
                AppBarActions(showBottomSheetDialog, navController, id)
            }
        )
    }
}

@Composable
fun AppBarActions(
    showBottomSheetDialog: () -> Unit,
    navController: NavHostController,
    id: Int
){
    SearchAction()
    ShareAction(showBottomSheetDialog)
    MoreAction(navController, id)
}

@Composable
fun SearchAction(){
    val context = LocalContext.current
    IconButton(
        onClick = {
            val activity = context as Activity

            thread {
                val database = LocalDB.getDatabase(activity as Context)
                val pokemonDao = database.pokemonDao()
                pokemonDao.deleteAllPokemons()
            }
            Toast.makeText(context,"Buscar???", Toast.LENGTH_SHORT).show()
        }
    ){
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "icono buscar",
            tint = Color.White
        )
    }
}

@Composable
fun ShareAction(showBottomSheetDialog: () -> Unit){
    val context = LocalContext.current
    var launched = false
    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {})
    IconButton(
        onClick = {
            showBottomSheetDialog()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "https://ulima.edu.pe")
            launcher.launch(intent)
            launched = true
        }
    ){
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "icono compartir",
            tint = Color.White
        )
    }
}

@Composable
fun MoreAction(
    navController: NavHostController,
    id: Int
){
    var expanded by remember { mutableStateOf(false)}
    val context = LocalContext.current

    IconButton(
        onClick = {
            expanded = true
        }
    ){
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "icono más",
            tint = Color.White
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    //context.startActivity(Intent(context, UploadActivity::class.java))
                    navController.navigate("/profile/?user_id=$id")
                }
            ){
                Text(
                    text = "Editar Perfil"
                )
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    (context as? Activity)?.run {
                        finishAffinity()
                    }
                }
            ){
                Text(
                    text = "Salir"
                )
            }
        }
    }
}
