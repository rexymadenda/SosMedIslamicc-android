package com.hariobudiharjo.sosmedislamic.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hariobudiharjo.sosmedislamic.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            login(edtEmail?.text.toString(), edtPassword?.text.toString())
        }
    }
    fun login(email: String, password: String) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}
