package net.hassan.shop.data.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import net.hassan.shop.R

class AddItemDialog(context: Context):AppCompatDialog(context) {
    lateinit var addTV :TextView
    lateinit var cancelTV :TextView
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add)

        addTV = findViewById(R.id.addTV)!!
        cancelTV = findViewById(R.id.cancelTV)!!



    }
}