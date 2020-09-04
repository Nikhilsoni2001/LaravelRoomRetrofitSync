package com.laravelroomretrofitsync.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.models.User
import com.laravelroomretrofitsync.ui.adapters.UserAdapter

class Main : Fragment() {

    private lateinit var recyclerMain: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private var list = arrayListOf<User>()

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
        list.add(User(1, "nikhil@gmail.com", "8699333207", "Active", "Nikhil"))
        list.add(User(2, "aditya@gmail.com", "4856589613", "Inactive", "Aditya"))
        list.add(User(3, "ishant@gmail.com", "5461956466", "Active", "Ishant"))

        if (activity != null) {
            userAdapter = UserAdapter(list)
            val mLayoutManager = LinearLayoutManager(activity)
            recyclerMain.layoutManager = mLayoutManager
            recyclerMain.itemAnimator = DefaultItemAnimator()
            recyclerMain.adapter = userAdapter
            recyclerMain.setHasFixedSize(true)
        }

    }
}