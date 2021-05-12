package com.example.ui2.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui2.R
import com.example.ui2.model.Index
import kotlinx.android.synthetic.main.item_rv_index_layout.view.*
import kotlinx.android.synthetic.main.load_more_rv_index_layout.view.*

class RecyclerViewIndexAdapter(var arrayList: ArrayList<Index>) :
    RecyclerView.Adapter<RecyclerViewIndexAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val tvLocal = view.tvLocal
        val tvTime = view.tvTime
        val tvPrice = view.tvPrice
        val tvState = view.tvState
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_index_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = arrayList[position].name + " " + (position + 1).toString()
        holder.tvLocal.text = arrayList[position].local
        holder.tvTime.text = arrayList[position].time
        holder.tvPrice.text = arrayList[position].price
        holder.tvState.text = arrayList[position].statePoint
    }

    fun deleteItem(pos: Int) {
        arrayList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}