package com.hariobudiharjo.sosmedislamic.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.adapter.MessageAdapter
import com.hariobudiharjo.sosmedislamic.model.messageModel
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

        btnSend.setOnClickListener {
            if(txtMessage.text.isNotEmpty()) {
                val message = messageModel(
                    "chatbot",
                    txtMessage.text.toString(),
                    Calendar.getInstance().timeInMillis
                )
                adapter.addMessage(message)

                val messagebot = messageModel(
                    "hario",
                    txtMessage.text.toString(),
                    Calendar.getInstance().timeInMillis
                )
                adapter.addMessage(messagebot)

            } else {
                Toast.makeText(applicationContext,"Message should not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
