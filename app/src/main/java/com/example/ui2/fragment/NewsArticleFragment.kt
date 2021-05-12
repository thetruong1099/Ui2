package com.example.ui2.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ui2.R
import com.example.ui2.util.CustomBackStackFragment
import kotlinx.android.synthetic.main.fragment_news_article.*


class NewsArticleFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_news_article, container, false)
        return view
    }

    @SuppressLint("RestrictedApi")
    override fun onStart() {
        super.onStart()


        val args: NewsArticleFragmentArgs by navArgs()
        var article = args.article
        tvArticle.text = article

        sharedPreferences = requireContext().getSharedPreferences("myData", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString("article", article)
        editor.commit()
    }


}