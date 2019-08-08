package com.hariobudiharjo.sosmedislamic.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hariobudiharjo.sosmedislamic.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
        thread.start()
    }
}
