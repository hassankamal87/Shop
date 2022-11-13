package net.hassan.shop.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.hassan.shop.R
import net.hassan.shop.data.db.entities.Item
import net.hassan.shop.data.ui.viewmodel.ItemViewModel

class ItemAdapter(private val itemViewModel: ItemViewModel) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var myList: List<Item> = listOf(Item("Starting",0))


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.nameTV.text = myList[position].name
        holder.amountTV.text = myList[position].amount.toString()

        holder.deleteIV.setOnClickListener(View.OnClickListener {
            itemViewModel.delete(myList[position])

        })

        holder.minusIV.setOnClickListener(View.OnClickListener {
            if (myList[position].amount > 0) {
                myList[position].amount--
                itemViewModel.insert(myList[position])

            }
        })

        holder.plusIV.setOnClickListener(View.OnClickListener {
            myList[position].amount++
            itemViewModel.insert(myList[position])

        })

    }

    override fun getItemCount(): Int {
        return myList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(myList: List<Item>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: TextView
        var amountTV: TextView
        var deleteIV: ImageView
        var plusIV: ImageView
        var minusIV: ImageView

        init {
            nameTV = itemView.findViewById(R.id.tvName)
            amountTV = itemView.findViewById(R.id.tvAmount)
            deleteIV = itemView.findViewById(R.id.ivDelete)
            plusIV = itemView.findViewById(R.id.ivPlus)
            minusIV = itemView.findViewById(R.id.ivMinus)
        }

    }


}