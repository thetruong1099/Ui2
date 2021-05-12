package com.example.ui2.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.ui2.R
import com.example.ui2.util.CustomBackStackFragment
import kotlinx.android.synthetic.main.fragment_coin.*
import kotlinx.coroutines.*

class CoinFragment : CustomBackStackFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_coin, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        fabAlarm.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
        fabAlarm.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(200)
                    fabAlarm.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#000000"))
                }
            }
            if (event.action == MotionEvent.ACTION_DOWN) fabAlarm.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#BB86FC"))
            false
        }
    }

}