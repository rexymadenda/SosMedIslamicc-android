package com.hariobudiharjo.sosmedislamic.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.adapter.MemberAdapter
import com.hariobudiharjo.sosmedislamic.model.grupModel
import com.hariobudiharjo.sosmedislamic.model.memberModel
import com.hariobudiharjo.sosmedislamic.model.messageModel
import com.hariobudiharjo.sosmedislamic.network.RetrofitClient
import com.hariobudiharjo.sosmedislamic.network.SosmedInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_member.*
import java.util.*

class MemberActivity : AppCompatActivity() {

    private lateinit var adapter: MemberAdapter
    private val listMember: ArrayList<memberModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
        memberList.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(listMember, this)
        memberList.adapter = adapter
        getMember("2")
    }


    @SuppressLint("CheckResult")
    fun getMember(id: String? = "1") {
        val getRecent = RetrofitClient.getRetrofitClient().create(SosmedInterface::class.java)
        getRecent.listmember(id!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    for (i in result.data!!) {
                        val data = memberModel(
                            i.uid!!,
                            i.gid!!,
                            ""
                        )

                        listMember.add(data)
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
