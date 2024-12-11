package uz.itteacher.mycontact1021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.itteacher.mycontact1021.db.AppDataBase
import uz.itteacher.mycontact1021.layout.MainContactScreen
import uz.itteacher.mycontact1021.navigation.NavHostContainer
import uz.itteacher.mycontact1021.ui.theme.MyContact1021Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val appDataBase = AppDataBase.getInstance(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyContact1021Theme {
                Column(modifier = Modifier.fillMaxSize().padding(vertical = 70.dp,
                    horizontal = 16.dp)) {
                    NavHostContainer(
                        navController = rememberNavController(), startDestination = "home_screen", appDataBase)
                }

            }
        }
    }
}


