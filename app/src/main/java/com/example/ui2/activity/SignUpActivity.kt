package com.example.ui2.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ui2.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        btnSignUp.setOnClickListener {
            var username = edtUsernameSignUp.text.toString()
            var password = edtPasswordSignUp.text.toString()

            if (username.length == 0 || password.length == 0) {
                showDialogEmptyBox("Không để trống Username,Password")
            } else if (password.length < 6) {
                showDialogPasswordLength()
            } else {
                editor.putString("username", username)
                editor.putString("password", password)
                editor.commit()

                var intent = Intent()
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        btnToLogin.setOnClickListener {
            onBackPressed()
        }

        btnForgotPasswordSignUp.setOnClickListener {
            var username = sharedPreferences.getString("username", "").toString()
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


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }

    private fun showDialogPasswordLength() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Password length greater than 6 ")
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun showDialogEmptyBox(string: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(string)
            .setPositiveButton("Yes"){dialogInterface, which -> }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}