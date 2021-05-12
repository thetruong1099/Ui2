package com.example.ui2.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ui2.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private val result_signup = 2403
    private lateinit var sharedPreferences: SharedPreferences
    private var username: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username", "").toString()
        password = sharedPreferences.getString("password", "").toString()

        btnLogin.setOnClickListener {
            var email = edtUsername.text.toString()
            var pass = edtPassword.text.toString()
            if (email.length == 0 || pass.length == 0) {
                showDialogEmptyBox("Not empty username and password")
            } else if (email == username && pass == password) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }else showDialogEmptyBox("Nhập Sai Username, Password")
        }

        btnToSignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, result_signup)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btnForgotPassword.setOnClickListener {
            var email = edtUsername.text.toString()
            if (email.length==0){
                showDialogEmptyBox("Cần nhập email")
            }
            else{
                if(username.length==0||username!=email){
                    showDialogEmptyBox("Không tồn tại tài khoản này")
                }
                else{
                    var intent = Intent(this, EmailActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }

    private fun showDialogEmptyBox(string: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(string)
            .setPositiveButton("Yes"){dialogInterface, which -> }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == result_signup && resultCode == Activity.RESULT_OK) {
            edtUsername.setText(data?.getStringExtra("username"))
            edtPassword.setText(data?.getStringExtra("password"))
        }
    }
}