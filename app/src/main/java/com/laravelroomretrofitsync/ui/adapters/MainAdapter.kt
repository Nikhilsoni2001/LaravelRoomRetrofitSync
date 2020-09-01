package com.laravelroomretrofitsync.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.models.MainData
import java.util.ArrayList

class MainAdapter(val context: Context, private var list: ArrayList<MainData>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textOne: TextView = view.findViewById(R.id.textOne)
        val textTwo: TextView = view.findViewById(R.id.textTwo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_single_row, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]

        holder.textOne.text = item.textOne
        holder.textTwo.text = item.textTwo
    }
}