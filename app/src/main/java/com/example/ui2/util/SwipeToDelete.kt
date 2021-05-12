package com.example.ui2.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ui2.adapter.RecyclerViewIndexAdapter

class SwipeToDelete(var adapter: RecyclerViewIndexAdapter): ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var pos:Int = viewHolder.adapterPosition
        adapter.deleteItem(pos)
    }
}