package net.hassan.shop.data.ui.main

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.hassan.shop.R
import net.hassan.shop.data.adapters.ItemAdapter
import net.hassan.shop.data.db.ItemsDatabase
import net.hassan.shop.data.db.entities.Item
import net.hassan.shop.data.repository.Repo
import net.hassan.shop.data.ui.viewmodel.ItemViewModel
import net.hassan.shop.data.ui.viewmodel.ItemViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var itemsDatabase: ItemsDatabase
    lateinit var repo: Repo
    lateinit var factory: ItemViewModelFactory
    lateinit var itemViewModel: ItemViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ItemAdapter
    lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsDatabase = ItemsDatabase.getInstance(this)
        repo = Repo(itemsDatabase)
        factory = ItemViewModelFactory(repo)
        itemViewModel = ViewModelProvider(this, factory)[ItemViewModel::class.java]

        recyclerView = findViewById(R.id.recycler)
        adapter = ItemAdapter(itemViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.IO) {
            itemViewModel.getAll()
            itemViewModel.itemsMutableStateFlow.collect {
                withContext(Dispatchers.Main) {
                    adapter.setList(it)
                }
            }
        }

        //add dialog
        fab = findViewById(R.id.addButton)
        fab.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_add)
            dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.background_dialog))
            dialog.show()

            val addTV = dialog.findViewById<TextView>(R.id.addTV)
            val cancelTV = dialog.findViewById<TextView>(R.id.cancelTV)
            val nameET = dialog.findViewById<EditText>(R.id.nameET)
            val amountET = dialog.findViewById<EditText>(R.id.amountET)

            addTV.setOnClickListener {
                val name = nameET.text.toString()
                val amount = amountET.text.toString()

                if (name != "" || amount != "") {
                    itemViewModel.insert(Item(name, amount.toInt()))
                    dialog.dismiss()
                }
                else{
                    Toast.makeText(this,"name and amount fields are required !",Toast.LENGTH_SHORT).show()
                }
            }

            cancelTV.setOnClickListener {
                dialog.dismiss()
            }
        }


    }
}