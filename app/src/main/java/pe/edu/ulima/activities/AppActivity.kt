package pe.edu.ulima.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.ulima.activities.ui.theme.ProgramMovilTheme
import pe.edu.ulima.navigations.AppNavigation
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel
import pe.edu.ulima.ui.app.viewmodels.ProfileViewModel

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pokemonScreenModel = PokemonViewModel()
        var pokemonDetailViewModel = PokemonDetailViewModel()
        var profileViewModel = ProfileViewModel()
        setContent {
            ProgramMovilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(
                        pokemonScreenModel = pokemonScreenModel,
                        pokemonDetailViewModel = pokemonDetailViewModel,
                        profileViewModel = profileViewModel
                    )
                }
            }
        }
    }
}
