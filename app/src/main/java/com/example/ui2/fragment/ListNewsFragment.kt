package com.example.ui2.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui2.R
import com.example.ui2.adapter.RecyclerViewNewsAdapter
import com.example.ui2.model.News
import kotlinx.android.synthetic.main.fragment_list_news.*
import kotlinx.android.synthetic.main.fragment_list_news.btnAlert
import kotlinx.android.synthetic.main.fragment_news.*

class ListNewsFragment : Fragment() {

    private var listNews = ArrayList<News>()
    private lateinit var recyclerViewNewsAdapter: RecyclerViewNewsAdapter

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_list_news, container, false)
        return view
    }

    @SuppressLint("RestrictedApi", "ResourceAsColor")
    override fun onStart() {
        super.onStart()

        btnAlert.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                btnAlert.setImageResource(R.drawable.baseline_campaign_white_36)
            }
            if (event.action == MotionEvent.ACTION_UP) {
                btnAlert.setImageResource(R.drawable.alert)
            }
            true
        }

        listNews.add(News("ATLANTIA", R.drawable.ad_1, "ALT -3,87%", "3 Sept 2020"))
        listNews.add(News("XIAOMI", R.drawable.ad_2, "HKD -2,13%", "2 Sept 2020"))
        listNews.add(News("APPLE", R.drawable.ad_3, "AAPL -0,91%", "APPLE"))

        recyclerViewNewsAdapter = RecyclerViewNewsAdapter(listNews)

        rvNews.apply {
            adapter = recyclerViewNewsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("myData", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        if (sharedPreferences.getBoolean("checked", false)) {
            editor.putBoolean("checked", false)
            editor.commit()

            var article = sharedPreferences.getString("article", "")!!
            val action =
                ListNewsFragmentDirections.actionListNewsFragmentToNewsArticleFragment(article)
            navController.navigate(action)
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onDetach() {
        super.onDetach()
        if (navController.backStack.size == 3) {
            editor.putBoolean("checked", true)
            editor.commit()
        }
    }
}