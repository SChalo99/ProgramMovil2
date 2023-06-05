package pe.edu.ulima.navigations

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.ui.app.uis.HomeScreen
import pe.edu.ulima.ui.app.uis.PokemonDetailScreen
import pe.edu.ulima.ui.app.uis.PokemonScreen
import pe.edu.ulima.ui.app.uis.ProfileScreen
import pe.edu.ulima.ui.app.viewmodels.HomeViewModel
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel
import pe.edu.ulima.ui.app.viewmodels.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigation(
    pokemonScreenModel: PokemonViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel,
    profileViewModel: ProfileViewModel
){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val pokemonIdParam = navBackStackEntry?.arguments?.getInt("pokemon_id")
    val userId = navBackStackEntry?.arguments?.getInt("user_id")

    NavHost(
        navController = navController,
        startDestination = "/pokemon"
    ){
        // vista para mostrar el listado de pokemones
        composable(
            route = "/pokemon"
        ){
            val context = LocalContext.current
            /*HomeViewModel().setPokemons(activity = context as Activity)*/
        /*
           PokemonScreen(
               viewModel = pokemonScreenModel,
               navController
           )*/
            HomeScreen(viewModel = HomeViewModel(), navController = navController)
        }
        // editar pokemon
        composable(
            route = "/pokemon/detalle?pokemon_id={pokemon_id}",
            arguments = listOf(
                navArgument("pokemon_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            Log.d("APP_NAVIGATION", pokemonIdParam.toString())
            pokemonDetailViewModel.getPokemon(pokemonIdParam!!)
            PokemonDetailScreen(
                viewModel = pokemonDetailViewModel
            )
        }
        // crear pokemon
        composable(
            route = "/pokemon/new",
            arguments = listOf(
            )
        ){
           /* pokemonDetailViewModel.unsetPokemon()
            PokemonDetailScreen(
                viewModel = pokemonDetailViewModel
            )*/
        }
        // profile
        composable(
            route = "/profile/?user_id={user_id}",
            arguments = listOf(
                navArgument("user_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            Log.d("APP_NAVIGATION", pokemonIdParam.toString())
            profileViewModel.setUsuario(userId!!)
            ProfileScreen(
                viewModel = profileViewModel
            )
        }
    }
}