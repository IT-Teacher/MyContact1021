package uz.itteacher.mycontact1021.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uz.itteacher.mycontact1021.model.MyContact
import uz.itteacher.mycontact1021.R

@Composable
fun MainContactScreen(myContactList: List<MyContact>,navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (myContactList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.box),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
            }

        }
        Row(modifier = Modifier.fillMaxSize().clickable{navController.navigate("create_contact")},
            horizontalArrangement = Arrangement.End) {
            FloatingActionButton(
                onClick = {navController.navigate("create_contact")
                },
                modifier = Modifier.padding(16.dp).align(Alignment.Bottom),
                containerColor = Color(0xFF2196F3)
            ) {

                Icon(Icons.Default.Add,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                    contentDescription = null,)
            }
        }
    }
}