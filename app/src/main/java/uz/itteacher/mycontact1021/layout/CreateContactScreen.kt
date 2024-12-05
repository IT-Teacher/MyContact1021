package uz.itteacher.mycontact1021.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateContactScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Row() {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Add",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.clickable{
                    navController.navigate("home_screen")
                }
            )


        }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Name",
            fontSize = 18.sp
        )
        TextField(
            value = name,
            onValueChange = {name = it},
            modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
            placeholder = { Text(text = "Enter Name") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(5.dp)

        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Surname",
            fontSize = 18.sp
        )
        TextField(
            value = surname,
            onValueChange = {surname = it},
            modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
            placeholder = { Text(text = "Enter surame") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(5.dp)

        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Phone number",
            fontSize = 18.sp
        )
        TextField(
            value = phone,
            onValueChange = {phone = it},
            modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
            placeholder = { Text(text = "+998 __ ___ __ __") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(5.dp),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone

            )
        )
    }
}

