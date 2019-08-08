package com.hariobudiharjo.sosmedislamic.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.network.SosmedInterface
import com.hariobudiharjo.sosmedislamic.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    fun init() {
        btn_signup.setOnClickListener {
            val nama = edtNama.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            val pass2 = edtPassword2.text.toString()
            if (TextUtils.isEmpty(nama)) {
                showBox("Nama Belum Diisi!")
            } else if (TextUtils.isEmpty(email)) {
                showBox("Email Belum Diisi!")
            } else if (TextUtils.isEmpty(pass)) {
                showBox("Password Belum Diisi!")
            } else if (TextUtils.isEmpty(pass2)) {
                showBox("Konfirmasi Password Belum Diisi!")
            } else if (pass != pass2) {
                showBox("Password dan Konfirmasi password tidak asam!")
            } else
                registrasi(nama, email, pass)
        }
    }

    @SuppressLint("CheckResult")
    private fun registrasi(nama: String, email: String, pass: String) {
        Log.d("DEBUG", "Registrasi")
        val getRecent = RetrofitClient.getRetrofitClient().create(SosmedInterface::class.java)
        getRecent.registrasi(nama, email, pass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.status!!) {
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
