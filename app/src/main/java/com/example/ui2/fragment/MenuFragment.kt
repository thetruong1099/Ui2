package com.example.ui2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui2.R
import com.example.ui2.adapter.RecyclerViewMenuAdpater
import com.example.ui2.model.MenuItem
import com.example.ui2.util.CustomBackStackFragment
//import com.example.ui2.util.removeElementOverlap
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : CustomBackStackFragment() {

    private var listMenuItem = ArrayList<MenuItem>()
    private lateinit var rvMenuAdapter: RecyclerViewMenuAdpater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onStart() {
        super.onStart()

        listMenuItem.add(MenuItem(R.drawable.icons_8_alarm, "Alerts", 0))
        listMenuItem.add(MenuItem(R.drawable.icons_8_left_and_right_arrows, "Predictions", 0))
        listMenuItem.add(MenuItem(R.drawable.icons_8_pin, "Saved elements", 0))
        listMenuItem.add(MenuItem(R.drawable.icons_8_no_entry, "Remove Ads", 0))
        listMenuItem.add(MenuItem(null, null, 3))
        listMenuItem.add(MenuItem(null, "Tools", 2))
        listMenuItem.add(MenuItem(R.drawable.icons_8_profit_2, "Select Stocks", 1))
        listMenuItem.add(MenuItem(R.drawable.icons_8_swap, "Currency Exchange", 1))
        listMenuItem.add(MenuItem(R.drawable.icons_8_video_call, "Webinar", 1))
        listMenuItem.add(MenuItem(R.drawable.icons_8_rent, "Best Broker", 1))
        listMenuItem.add(MenuItem(null, null, 3))
        listMenuItem.add(MenuItem(null, "Markets", 2))
        listMenuItem.add(MenuItem(R.drawable.icons_8_profit_2, "Select Stocks", 1))

        rvMenuAdapter = RecyclerViewMenuAdpater(listMenuItem)

        rvMenu.apply {
            adapter = rvMenuAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
}