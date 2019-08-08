package com.hariobudiharjo.sosmedislamic.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.adapter.GrupAdapter
import com.hariobudiharjo.sosmedislamic.model.grupModel
import com.hariobudiharjo.sosmedislamic.network.RetrofitClient
import com.hariobudiharjo.sosmedislamic.network.SosmedInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_grup.*

class GrupActivity : AppCompatActivity() {

    private lateinit var adapter: GrupAdapter
    private val listGrup: ArrayList<grupModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grup)

        rv_grup.layoutManager = LinearLayoutManager(this)
        adapter = GrupAdapter(listGrup, this)
        rv_grup.adapter = adapter
        getGrup()
    }

    @SuppressLint("CheckResult")
    fun getGrup() {
        val getRecent = RetrofitClient.getRetrofitClient().create(SosmedInterface::class.java)
        getRecent.listgrup()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    for (i in result.data!!) {
                        val data = grupModel(
                            i.id!!,
                            i.nama!!,
                            i.deskripsi!!
                        )

                        listGrup.add(data)
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
