package com.example.ui2.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.ui2.R
import com.example.ui2.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_onboarding2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OnboardingFragment2 : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_onboarding2, container, false)



        return view
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = requireActivity().getSharedPreferences("myData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        btnSkipOnbroad2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                editor.putBoolean("statusOnboarding", true)
                editor.commit()
            }

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            this.requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            requireActivity().finish()
        }

        btnNextOnbroad2.setOnClickListener {
            var onboardingFragment3= OnbroadingFragment3()
            setfragment(onboardingFragment3)
        }
    }

    private fun setfragment(fragment: Fragment){
        var fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        fragmentTransaction.replace(R.id.frame_Onboard, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}