package uz.itteacher.mycontact1021.layout

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import uz.itteacher.mycontact1021.db.AppDataBase
import uz.itteacher.mycontact1021.model.MyContact
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateContactScreen(navController: NavController, appDataBase: AppDataBase, id: Int) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("Add") }
    var isEdit by remember { mutableStateOf(false) }
    var image by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    fun saveImageToAppDirectory(context: Context, uri: Uri): Uri? {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri) ?: return null
        val appDir = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyAppImages")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        val file = File(appDir, "image_${System.currentTimeMillis()}.jpg")
        return try {
            val outputStream: OutputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            outputStream.close()
            inputStream.close()
            Uri.fromFile(file)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    val galleryLauncher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                saveImageToAppDirectory(context, uri)?.let { savedUri ->
                    imageUri = savedUri
                }
            }
        }
    }

    if (id != -1 && !isEdit) {
        val contact: MyContact? = appDataBase.myContactDao().getContactById(id)
        if (contact != null) {
            name = contact.name
            surname = contact.surname
            phone = contact.phoneNumber
            image = contact.image ?: ""
            title = "Edit"
        }
        isEdit = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        navController.navigate("home_screen")
                    }
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.clickable {
                    val contact: MyContact? =
                        appDataBase.myContactDao().getContactByPhoneNumber(phone)
                    if (contact == null || isEdit) {
                        if (isValid(name, phone)) {
                            if (isEdit) {
                                appDataBase.myContactDao().updateContact(
                                    id,
                                    name,
                                    surname,
                                    phone
                                )
                            } else {
                                appDataBase.myContactDao().addContact(
                                    MyContact(
                                        name = name,
                                        surname = surname,
                                        phoneNumber = phone,
                                        image = imageUri.toString()
                                    )
                                )
                            }
                            navController.navigate("home_screen")
                        }
                    }else{
//                        AlertDialog()
                    }
                }
            )


        }

        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            if (imageUri != null){
                Log.d("TAG", "CreateContactScreen: $imageUri")
                AsyncImage(
                    model = imageUri.toString(),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                        .clip(CircleShape),
                )
            }else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
            IconButton(onClick = {

                val galleryIntent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                galleryLauncher.launch(galleryIntent)

            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        Text(
            text = "Name",
            fontSize = 18.sp
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
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
            onValueChange = { surname = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
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
            onValueChange = { phone = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
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

fun isValid(name: String, phone: String): Boolean {
    if (name.isEmpty() || phone.isEmpty()) {
        return false
    }

    if (phone.length != 13) {
        return false
    }

    if (!phone.startsWith("+998")) {
        return false
    }

    if (!phone.substring(1).isDigitsOnly()) {
        return false
    }


    return true

}

