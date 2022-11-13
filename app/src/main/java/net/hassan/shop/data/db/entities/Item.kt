package net.hassan.shop.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
class Item(
    @ColumnInfo(name = "name_column")
    var name:String,
    @ColumnInfo(name = "amount_column")
    var amount:Int ) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}