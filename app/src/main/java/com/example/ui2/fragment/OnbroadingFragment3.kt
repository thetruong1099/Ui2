package com.example.ui2.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ui2.R
import com.example.ui2.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_onbroading3.*
import kotlinx.android.synthetic.main.fragment_onbroading3.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnbroadingFragment3 : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_onbroading3, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        sharedPreferences = requireActivity().getSharedPreferences("myData", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        btnSkipOnbroad3.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                editor.putBoolean("statusOnboarding", true)
                editor.commit()
            }

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            requireActivity().finish()
        }

        btnStart.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                editor.putBoolean("statusOnboarding", false)
                editor.commit()
            }
            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            requireActivity().finish()
        }
    }

}