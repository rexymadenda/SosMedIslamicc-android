package com.hariobudiharjo.sosmedislamic.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.network.SosmedInterface
import com.hariobudiharjo.sosmedislamic.utils.SPManager
import id.bigio.jakarta.ppid.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var spManager: SPManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        spManager = SPManager(this)


        btn_login.setOnClickListener {
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            if (TextUtils.isEmpty(email)) {
                showBox("Email Belum di isi!")
            } else if (TextUtils.isEmpty(pass)) {
                showBox("Password Belum di isi!")
            } else
                login(email, pass)
        }
    }

//    fun login(email: String, password: String) {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//
//    }

    @SuppressLint("CheckResult")
    private fun login(email: String, pass: String) {
        Log.d("DEBUG", "Registrasi")
        val getRecent = RetrofitClient.getRetrofitClient().create(SosmedInterface::class.java)
        getRecent.login(email, pass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.status!!) {
                        spManager?.saveSPSudahLogin(true)
                        spManager?.saveSPGid(result.gid!!)
                        spManager?.saveSPUid(result.id!!)
                        showBoxFinish(result.message!!)
                        Log.d("DEBUG", result.toString())
                    } else {
                        showBox(result.message!!)
                        Log.d("DEBUG", result.toString())
                    }
                },
                { error ->
                    showBox(error.message.toString())
                    Log.d("DEBUG", "GAGAL : ${error.message}")
                }
            )
    }

    private fun showBoxFinish(message: String) {
        val dialogBox: AlertDialog.Builder = AlertDialog.Builder(this, R.style.customAlertDialog)
        dialogBox.setTitle("Nice!")
        dialogBox.setMessage(message)
        dialogBox.setCancelable(false)
        dialogBox.setPositiveButton("OK") { _, _ -> this.finish() }
        dialogBox.show()
    }


    private fun showBox(message: String) {
        val dialogBox: AlertDialog.Builder = AlertDialog.Builder(this, R.style.customAlertDialog)
        dialogBox.setTitle("Oops!")
        dialogBox.setMessage(message)
        dialogBox.setCancelable(false)
        dialogBox.setPositiveButton("OK") { _, _ -> }
        dialogBox.show()
    }
}
