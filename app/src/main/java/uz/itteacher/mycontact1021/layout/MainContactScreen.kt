package uz.itteacher.mycontact1021.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uz.itteacher.mycontact1021.model.MyContact
import uz.itteacher.mycontact1021.R
import uz.itteacher.mycontact1021.db.AppDataBase
import uz.itteacher.mycontact1021.layout.components.ContactCard

@Composable
fun MainContactScreen(navController: NavHostController, appDataBase: AppDataBase) {
    val myContactList = appDataBase.myContactDao().getAllContacts().sortedBy { it.name }
    val isSearchActive = remember { mutableStateOf(false) }
    val searchQuery = remember { mutableStateOf("") }
    val sortedList =  if(searchQuery.value.isNotBlank() && searchQuery.value.isNotEmpty()) myContactList.filter { it.name.contains(searchQuery.value, ignoreCase = true) } else myContactList
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Contacts",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Row() {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            isSearchActive.value = !isSearchActive.value
                        }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable {

                        }
                    )

                }


            }
            if(isSearchActive.value){
                Spacer(Modifier.height(12.dp))
                TextField(
                    value = searchQuery.value,
                    onValueChange = {
                        searchQuery.value = it
                    },
                    placeholder = {
                        Text("Search...",
                            color = Color.Gray)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
                )
            }
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
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sortedList) { contact ->
                    ContactCard(contact, onCallClick = {
                        navController.navigate("info_contact/${contact.id}")
                    })
                }
            }
        }


    }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate("create_contact/-1")
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Bottom),
            containerColor = Color(0xFF2196F3)
        ) {

            Icon(
                Icons.Default.Add,
                tint = Color.White,
                modifier = Modifier.size(24.dp),
                contentDescription = null,
            )
        }
    }
}