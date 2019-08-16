package com.hariobudiharjo.sosmedislamic.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.adapter.MessageAdapter
import com.hariobudiharjo.sosmedislamic.model.grupModel
import com.hariobudiharjo.sosmedislamic.model.messageModel
import com.hariobudiharjo.sosmedislamic.network.RetrofitClient
import com.hariobudiharjo.sosmedislamic.network.SosmedInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageList.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        messageList.adapter = adapter

        btn_member.setOnClickListener {
            goToMember("!")
        }

        btnSend.setOnClickListener {
            if (txtMessage.text.isNotEmpty()) {
                val message = messageModel(
                    "chatbot",
                    txtMessage.text.toString(),
                    Calendar.getInstance().timeInMillis.toString()
                )
                adapter.addMessage(message)

                val messagebot = messageModel(
                    "hario",
                    txtMessage.text.toString(),
                    Calendar.getInstance().timeInMillis.toString()
                )
                adapter.addMessage(messagebot)

            } else {
                Toast.makeText(applicationContext, "Message should not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMember(gid: String) {
        val intent = Intent(this, MemberActivity::class.java)
        intent.putExtra("gid", gid)
        startActivity(intent)
    }

    @SuppressLint("CheckResult")
    fun getMessage(id: String? = "1") {
        val getRecent = RetrofitClient.getRetrofitClient().create(SosmedInterface::class.java)
        getRecent.listchat(id!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    for (i in result.data!!) {
                        adapter.addMessage(
                            messageModel(
                                i.uid!!,
                                i.message!!,
                                i.waktu!!
                            )
                        )
                    }
                    adapter.notifyDataSetChanged()


                    Log.d("DEBUG", result.toString())
                },
                { error ->
                    Log.d("DEBUG", "GAGAL : ${error.message}")
                }
            )
    }
}
