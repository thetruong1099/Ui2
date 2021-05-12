package com.example.ui2.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ui2.R
import kotlinx.android.synthetic.main.activity_email.*
import kotlinx.coroutines.*

class EmailActivity : AppCompatActivity() {
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        job = Job()
        counterTime()

        btnResendemail.setOnClickListener {
            job.cancel()
            intentToConfirmPassword()
        }

    }

    override fun onStop() {
        super.onStop()
        job.cancel()

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun counterTime() {
        job = CoroutineScope(Dispatchers.Default).launch {
            var numTime = 31
            repeat(31) {
                numTime--
                delay(1000)
                updateUi(numTime)
            }
            if (numTime == 0) intentToConfirmPassword()
        }
    }


    private suspend fun updateUi(number: Int) {
        withContext(Dispatchers.Main) {
            tvCounterTimer.text = "Wait $number seconds before sending it"
        }
    }

    private fun intentToConfirmPassword() {
        resetPassword()
        var intent = Intent(this, ConfirmPasswordChangedActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun resetPassword() {
        val sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("password", "1234567")
        editor.commit()
    }
}