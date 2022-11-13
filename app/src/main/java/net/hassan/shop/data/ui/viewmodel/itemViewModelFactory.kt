package net.hassan.shop.data.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.hassan.shop.data.repository.Repo

@Suppress("UNCHECKED_CAST")
class ItemViewModelFactory(private val repo: Repo):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(repo) as T
    }
}