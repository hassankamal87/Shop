package net.hassan.shop.data.db

import androidx.room.*
import net.hassan.shop.data.db.entities.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Query("SELECT * From items_table")
    fun getAllItems(): List<Item>
}