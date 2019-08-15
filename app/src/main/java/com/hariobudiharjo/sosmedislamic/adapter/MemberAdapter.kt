package com.hariobudiharjo.sosmedislamic.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hariobudiharjo.sosmedislamic.R
import com.hariobudiharjo.sosmedislamic.activity.MainActivity
import com.hariobudiharjo.sosmedislamic.model.grupModel
import com.hariobudiharjo.sosmedislamic.model.memberModel
import kotlinx.android.synthetic.main.rv_list_grup.view.*

class MemberAdapter(val items: ArrayList<memberModel>, val context: Context) :
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_list_grup, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]

        holder.judul?.text = data.nama
        holder.desc?.text = data.email
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val judul = view.tv_judul
        val desc = view.tv_deskripsi
    }
}