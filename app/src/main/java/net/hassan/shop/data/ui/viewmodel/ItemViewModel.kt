package net.hassan.shop.data.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import net.hassan.shop.data.db.entities.Item
import net.hassan.shop.data.repository.Repo

class ItemViewModel(private val repo: Repo):ViewModel() {
    var itemsMutableStateFlow = MutableStateFlow<List<Item>>(listOf<Item>())

    fun insert(item: Item){
        GlobalScope.launch(Dispatchers.IO) {
            repo.insertItem(item)
            getAll()
        }
    }

    fun delete(item: Item){
        GlobalScope.launch(Dispatchers.IO) {
            repo.deleteItem(item)
            getAll()
        }
    }

   suspend fun getAll(){
        //GlobalScope.launch(Dispatchers.IO) {
            itemsMutableStateFlow.emit(repo.getAllItems())
        //}
    }
}