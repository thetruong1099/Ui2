package com.example.ui2.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui2.R
import com.example.ui2.adapter.RecyclerViewIndexAdapter
import com.example.ui2.model.Index
import com.example.ui2.util.SwipeToDelete
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var listIndex = ArrayList<Index>()
    private lateinit var rvIndexAdapter: RecyclerViewIndexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
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

            if (event.action == MotionEvent.ACTION_DOWN) {
                fabAlarm.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#BB86FC"))
            }
            false
        }

        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("FTSE 100", "LONDON", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("IBEX 35", "MADRID", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DAX", "XETRA", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))
        listIndex.add(Index("DOWN JONES", "NYSE", "20.047,50", "10:44:45", "+203 (+1,04%)"))

        rvIndexAdapter = RecyclerViewIndexAdapter(listIndex)

        rvIndex.apply {
            adapter = rvIndexAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        btnLoadMore.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                repeat(10) {
                    listIndex.add(
                        Index(
                            "DOWN JONES",
                            "NYSE",
                            "20.047,50",
                            "10:44:45",
                            "+203 (+1,04%)"
                        )
                    )
                }
                delay(200)
                rvIndexAdapter.notifyDataSetChanged()
            }
        }

        var itemTouchHelper = ItemTouchHelper(SwipeToDelete(rvIndexAdapter))
        itemTouchHelper.attachToRecyclerView(rvIndex)

    }
}