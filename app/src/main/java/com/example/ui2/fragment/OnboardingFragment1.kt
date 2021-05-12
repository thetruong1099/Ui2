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
import kotlinx.android.synthetic.main.fragment_onboarding1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingFragment1() : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view:View = inflater.inflate(R.layout.fragment_onboarding1, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        sharedPreferences = requireActivity().getSharedPreferences("myData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        btnSkip.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                editor.putBoolean("statusOnboarding", true)
                editor.commit()
            }

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            requireActivity().finish()
        }

        btnNext.setOnClickListener {
            var onboardingFragment2= OnboardingFragment2()
            setfragment(onboardingFragment2)
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