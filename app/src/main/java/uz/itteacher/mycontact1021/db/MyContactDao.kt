package uz.itteacher.mycontact1021.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itteacher.mycontact1021.model.MyContact

@Dao
interface MyContactDao {

    @Query("SELECT * FROM my_contacts")
    fun getAllContacts(): List<MyContact>

    @Query("SELECT * FROM my_contacts WHERE id = :id")
    fun getContactById(id: Int): MyContact?

    @Query("DELETE FROM my_contacts WHERE id = :id")
    fun deleteContactById(id: Int)

    @Insert
    fun addContact(contact: MyContact)





}