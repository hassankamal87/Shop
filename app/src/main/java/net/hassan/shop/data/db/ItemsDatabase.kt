package net.hassan.shop.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.hassan.shop.data.db.entities.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemsDatabase:RoomDatabase() {
    abstract fun ItemDao(): ItemDao

    companion object{
        private var instance: ItemsDatabase? = null

        public fun getInstance(context: Context): ItemsDatabase {
            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext,
                    ItemsDatabase::class.java,"items_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as ItemsDatabase
        }
    }
}