package uz.itteacher.mycontact1021.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_contacts")
data class MyContact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surname: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val image: String? = null)
