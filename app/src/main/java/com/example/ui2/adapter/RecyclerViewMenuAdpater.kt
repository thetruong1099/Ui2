package com.example.ui2.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ui2.R
import com.example.ui2.model.MenuItem
import kotlinx.android.synthetic.main.item_funtion_rv_menu_layout.view.*
import kotlinx.android.synthetic.main.item_funtion_rv_menu_layout.view.tvName
import kotlinx.android.synthetic.main.item_group_name_rv_menu_layout.view.*
import kotlinx.android.synthetic.main.item_tool_rv_menu_layout.view.*

class RecyclerViewMenuAdpater(var arrayList: ArrayList<MenuItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemFunViewType = 0
    private val itemToolViewType = 1
    private val itemGroupNameViewType = 2
    private val itemDividerViewType = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == itemFunViewType) {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_funtion_rv_menu_layout, parent, false)
            return ItemFunViewHolder(itemView)
        }
        if (viewType == itemToolViewType) {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_tool_rv_menu_layout, parent, false)
            return ItemToolViewHolder(itemView)
        }
        if (viewType == itemGroupNameViewType) {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_group_name_rv_menu_layout, parent, false)
            return ItemGroupNamelViewHolder(itemView)
        } else {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_divider_rv_menu_layout, parent, false)
            return ItemDividerViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemFunViewHolder) {
            holder.fabFun.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#de1db8"))
            holder.fabFun.setImageResource(arrayList[position].iconImg!!)
            holder.fabFun.compatElevation = 0.0f
            holder.fabFun.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    holder.fabFun.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#de1db8"))
                }
                if (event.action == MotionEvent.ACTION_DOWN) {
                    holder.fabFun.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#e67e22"))
                }
                false
            }
            holder.tvNameFun.text = arrayList[position].name
        }
        if (holder is ItemToolViewHolder) {
            holder.imgIcon.setImageResource(arrayList[position].iconImg!!)
            holder.tvNameTool.text = arrayList[position].name
        }
        if (holder is ItemGroupNamelViewHolder) {
            holder.tvNameGroup.text = arrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (arrayList[position].type == 0) return itemFunViewType
        if (arrayList[position].type == 1) return itemToolViewType
        if (arrayList[position].type == 2) return itemGroupNameViewType
        return itemDividerViewType
    }

    private class ItemFunViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fabFun = view.fabFun
        var tvNameFun = view.tvName
    }

    private class ItemToolViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgIcon = view.imgIcon
        var tvNameTool = view.tvNameTool
    }

    private class ItemGroupNamelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvNameGroup = view.tvGroupName
    }

    private class ItemDividerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}