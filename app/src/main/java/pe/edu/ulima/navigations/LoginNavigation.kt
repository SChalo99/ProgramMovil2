package pe.edu.ulima.navigations

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.ui.login.uis.LoginScreen
import pe.edu.ulima.ui.login.uis.ResetPasswordScreen
import pe.edu.ulima.ui.login.uis.SplashScreen
import pe.edu.ulima.ui.login.viewmodels.LoginViewModel
import pe.edu.ulima.ui.login.viewmodels.ResetPasswordScreenViewModel

@Composable
fun LoginNavigation(
    loginScreenViewModel: LoginViewModel,
    resetPasswordScreenViewModel: ResetPasswordScreenViewModel,
){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val parameter = navBackStackEntry?.arguments?.getString("parameter")
    val optionalParameter = navBackStackEntry?.arguments?.getString("optionalParameter")

    NavHost(
        navController = navController,
        startDestination = "/"
    ){
        composable(
            route = "/login/{parameter}?optionalParameter={optionalParameter}",
            arguments = listOf(
                navArgument("parameter") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("optionalParameter") {
                    type = NavType.StringType
                    defaultValue = "default_value"
                }
            )
        ){ entry ->
            Log.d("pe.edu.ulima", "1 +++++++++++++++++++++++++++++++++++++++++++")
            Log.d("pe.edu.ulima", parameter.toString())
            Log.d("pe.edu.ulima", optionalParameter.toString())
            Log.d("pe.edu.ulima", "2 +++++++++++++++++++++++++++++++++++++++++++")
            if(parameter == null || parameter == ""){
                LoginScreen(
                    loginScreenViewModel,
                    goToResetPasswordScreen = {
                        navController.navigate("/reset_password")
                    }
                )
            }else{
                loginScreenViewModel.updateUsuario(parameter)
                LoginScreen(
                    loginScreenViewModel,
                    goToResetPasswordScreen = {
                        navController.navigate("/reset_password")
                    }
                )
            }
        }
        composable(
            route = "/",
            arguments = listOf()
        ){ entry ->
            SplashScreen(navController)
        }
        composable(
            route = "/login/",
            arguments = listOf()
        ){ entry ->
            LoginScreen(
                loginScreenViewModel,
                goToResetPasswordScreen = {
                    navController.navigate("/reset_password")
                }
            )
        }
        composable(
            route = "/reset_password",
            arguments = listOf()
        ){ entry ->
            ResetPasswordScreen(
                resetPasswordScreenViewModel,
                goToLoginScreen = {
                    Log.d("pe.edu.ulima", resetPasswordScreenViewModel.correo.value.toString())
                    val parameter = resetPasswordScreenViewModel.correo.value.toString()
                    navController.navigate("/login/$parameter")
                }
            )
        }
    }
}
