package uz.itteacher.mycontact1021.layout.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.itteacher.mycontact1021.model.MyContact

@Composable
fun ContactCard(contact: MyContact, onCallClick: (id:Int) -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        val fullName = "${contact.name} ${contact.surname}"
        Row (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(0.9f).clickable {
                onCallClick(contact.id)
            }
        ){
            Icon(imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(48.dp))

            Column {
                Text(fullName,
                    fontSize = 16.sp,)
                Text(contact.phoneNumber,
                    fontSize = 14.sp,
                    color = Color.Gray)
            }
        }
        Icon(imageVector = Icons.Default.Phone,
            null,
            modifier = Modifier.size(32.dp),
            tint = Color.Green)
    }
}
