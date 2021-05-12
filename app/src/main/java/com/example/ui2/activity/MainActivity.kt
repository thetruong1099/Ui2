package com.example.ui2.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ui2.R
import com.example.ui2.util.CustomBackStackFragment
import com.example.ui2.util.popBackStackAllInstances
import com.example.ui2.util.removeElementOverlap
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHost.navController

        var sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    bottomNavigationView.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.item_bottom_navigation_color)
                    bottomNavigationView.itemBackground =
                        ContextCompat.getDrawable(this, R.drawable.item_bottom_nav_background)
                }
                R.id.coinFragment -> {
                    bottomNavigationView.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.item_bottom_navigation_color)
                    bottomNavigationView.itemBackground =
                        ContextCompat.getDrawable(this, R.drawable.item_bottom_nav_background)
                }
                R.id.newsFragment -> {
                    bottomNavigationView.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.item_bottom_navigation_color)
                    bottomNavigationView.itemBackground =
                        ContextCompat.getDrawable(this, R.drawable.item_bottom_nav_background)
                }

                R.id.menuFragment -> {
                    bottomNavigationView.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.item_bottom_navigation_color)
                    bottomNavigationView.itemBackground =
                        ContextCompat.getDrawable(this, R.drawable.item_bottom_nav_background)
                }
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.homeFragment
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setItemOnTouchListener(R.id.newsFragment) { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                editor.putBoolean("checked", false)
                editor.commit()
            }
            false
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onBackPressed() {
        if (navController.backStack.size == 2) showDialogEmptyBox()
        else super.onBackPressed()
    }

    private fun showDialogEmptyBox() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to exit app?")
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            System.exit(0)
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
