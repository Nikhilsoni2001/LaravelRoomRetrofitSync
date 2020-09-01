package com.laravelroomretrofitsync.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.models.MainData
import com.laravelroomretrofitsync.ui.adapters.MainAdapter

class Main : Fragment() {

    private lateinit var recyclerMain: RecyclerView
    private lateinit var mainAdapter: MainAdapter
    private var list = arrayListOf<MainData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // calling function for setting up recyclerView
        setUpRecyclerview(view)


        return view
    }

    private fun setUpRecyclerview(view: View) {
        recyclerMain = view.findViewById(R.id.recyclerMain)

        // adding elements to list
        list.add(MainData("One", "this is 1"))
        list.add(MainData("Two", "this is 2"))
        list.add(MainData("Three", "this is 3"))

        if (activity != null) {
            mainAdapter = MainAdapter(activity as Context, list)
            val mLayoutManager = LinearLayoutManager(activity)
            recyclerMain.layoutManager = mLayoutManager
            recyclerMain.itemAnimator = DefaultItemAnimator()
            recyclerMain.adapter = mainAdapter
            recyclerMain.setHasFixedSize(true)
        }

    }
}