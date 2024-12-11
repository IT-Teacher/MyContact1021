package uz.itteacher.mycontact1021.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.itteacher.mycontact1021.model.MyContact

@Database(entities = [MyContact::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun myContactDao(): MyContactDao

    companion object {
        const val DB_NAME = "my_contact_db"
        private var instance: AppDataBase? = null

        fun getInstance(contex: Context):AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(contex, AppDataBase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }


            return instance!!
        }


    }


}