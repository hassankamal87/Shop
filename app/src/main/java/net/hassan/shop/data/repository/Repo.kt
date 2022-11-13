package net.hassan.shop.data.repository

import net.hassan.shop.data.db.ItemsDatabase
import net.hassan.shop.data.db.entities.Item

class Repo(private val itemsDatabase: ItemsDatabase) {

    fun insertItem(item: Item){
        itemsDatabase.ItemDao().insertItem(item)
    }

    fun deleteItem(item: Item){
        itemsDatabase.ItemDao().deleteItem(item)
    }

    fun getAllItems():List<Item>{
        return  itemsDatabase.ItemDao().getAllItems()
    }
}