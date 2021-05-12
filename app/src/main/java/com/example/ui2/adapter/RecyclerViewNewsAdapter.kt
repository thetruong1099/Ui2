package com.example.ui2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ui2.R
import com.example.ui2.fragment.ListNewsFragmentDirections
import com.example.ui2.model.News
import kotlinx.android.synthetic.main.item_rv_news_background.view.*

class RecyclerViewNewsAdapter(var arrayList: ArrayList<News>):
    RecyclerView.Adapter<RecyclerViewNewsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvTitle = view.tvTitle
        var imgAD = view.imgAd
        var tvDiscount = view.tvDiscount
        var tvDate = view.tvDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_news_background, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = arrayList[position].title
        holder.imgAD.setImageResource(arrayList[position].image)
        holder.tvDiscount.text = arrayList[position].discount
        holder.tvDate.text = arrayList[position].date



        holder.imgAD.setOnClickListener {
            var navController = it.findNavController()
            var article = arrayList[position].title
            val action = ListNewsFragmentDirections.actionListNewsFragmentToNewsArticleFragment(article)
            navController.navigate(action)
        }
    }
}