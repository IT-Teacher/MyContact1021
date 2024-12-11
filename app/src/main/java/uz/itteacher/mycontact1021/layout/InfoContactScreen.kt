package uz.itteacher.mycontact1021.layout

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import uz.itteacher.mycontact1021.db.AppDataBase

@Composable
fun InfoContactScreen(navController: NavController, appDataBase: AppDataBase, id: Int) {
    val contact = appDataBase.myContactDao().getContactById(id)

}