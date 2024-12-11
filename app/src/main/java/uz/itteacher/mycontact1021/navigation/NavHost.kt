package uz.itteacher.mycontact1021.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itteacher.mycontact1021.db.AppDataBase
import uz.itteacher.mycontact1021.layout.CreateContactScreen
import uz.itteacher.mycontact1021.layout.MainContactScreen

@Composable
fun NavHostContainer (navController: NavHostController, startDestination: String, appDataBase: AppDataBase ){
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home_screen") {
            MainContactScreen(navController, appDataBase)
        }
        composable("create_contact") {
            CreateContactScreen(navController , appDataBase)
    }
    }
}

