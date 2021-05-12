package com.example.ui2.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.ui2.R
import java.util.*
import kotlin.collections.ArrayList

fun NavController.popBackStackAllInstances(destination: Int, inclusive: Boolean): Boolean {
    var popped: Boolean
    while (true) {
        popped = popBackStack(destination, inclusive)
        if (!popped) {
            break
        }
    }
    return popped
}

@SuppressLint("RestrictedApi")
fun NavController.removeElementOverlap(navController: NavController): Boolean {
    var arr = ArrayList<NavBackStackEntry>()
    var backstack = navController.backStack
    for (i in backstack) arr.add(i)
    var dem = 2
    var checkDuplicate = false
    var checkHomeFragment = false
    for (i in 3 until arr.size - 1) {
        if (arr[i].destination.id == R.id.homeFragment) {
            checkHomeFragment = true
            break
        }
    }

    for (i in 2 until arr.size - 1) {
        dem++
        if (arr[i].destination.id == arr[arr.size - 1].destination.id) {
            if (checkHomeFragment) {
                navController.popBackStack(R.id.homeFragment, true)
                navController.popBackStack(arr[i].destination.id, true)
                checkHomeFragment = false
                checkDuplicate = true
                break
            } else {
                navController.popBackStackAllInstances(arr[arr.size - 1].destination.id, true)
                checkDuplicate = true
                break
            }
        }
    }
    if (checkDuplicate) {
        for (i in dem until arr.size) {
            navController.navigate(arr[i].destination.id)
        }
    }

    if (arr.size == 3 && arr[2].destination.id == R.id.homeFragment) {
        navController.popBackStack(R.id.homeFragment, true)
    }

    return checkDuplicate
}

