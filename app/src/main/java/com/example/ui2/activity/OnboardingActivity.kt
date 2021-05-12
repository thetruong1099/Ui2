package com.example.ui2.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ui2.R
import com.example.ui2.fragment.OnboardingFragment1
import kotlinx.android.synthetic.main.fragment_onboarding1.*

class OnboardingActivity : AppCompatActivity() {

    private val onboarding1 = OnboardingFragment1()
    private lateinit var sharePreference: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        sharePreference = getSharedPreferences("myData", Context.MODE_PRIVATE)
        if (sharePreference == null) setfragment(onboarding1)
        else {
            if (sharePreference.getBoolean("statusOnboarding", true) == true) setfragment(onboarding1)
            else setIntentToLogin()
        }
    }

    private fun setfragment(fragment: Fragment) {
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_Onboard, fragment)
        fragmentTransaction.commit()
    }

    private fun setIntentToLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}