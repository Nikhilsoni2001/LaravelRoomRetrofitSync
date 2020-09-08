package com.laravelroomretrofitsync.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.models.User
import com.laravelroomretrofitsync.ui.UserActivity
import com.laravelroomretrofitsync.ui.UserViewModel
import com.laravelroomretrofitsync.ui.adapters.UserAdapter
import com.laravelroomretrofitsync.util.Resource

class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var llLoading: LinearLayout
    private lateinit var recyclerMain: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private var usersList = arrayListOf<User>()
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        viewModel = (activity as UserActivity).viewModel


        initialization(view)

        // this is for testing purpose i will change this code
        //  viewModel.deleteUser(6)

        viewModel.userData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { userResponse ->
                        for (user in userResponse.user) {
                            val users = User(
                                user.id,
                                user.user_email,
                                user.user_phone,
                                user.user_status,
                                user.user_name
                            )
                            usersList.add(users)
                            setUpRecyclerview(usersList)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("ERROR", "Error is => $message")
                        Toast.makeText(activity as Context, "Error => $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        // floating action button listener
        fab.setOnClickListener {
            openCreateUserFragment()
        }
        return view
    }

    private fun initialization(view: View) {
        llLoading = view.findViewById(R.id.llLoading)
        fab = view.findViewById(R.id.fab)
        recyclerMain = view.findViewById(R.id.recyclerMain)
    }

    private fun setUpRecyclerview(userList: ArrayList<User>) {

        if (activity != null) {
            userAdapter = UserAdapter(activity as Context, userList)
            val mLayoutManager = LinearLayoutManager(activity)
            recyclerMain.layoutManager = mLayoutManager
            recyclerMain.itemAnimator = DefaultItemAnimator()
            recyclerMain.adapter = userAdapter
            recyclerMain.setHasFixedSize(true)
        }
    }

    private fun hideProgressBar() {
        llLoading.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        llLoading.visibility = View.VISIBLE
    }

    // function to open createNewUserFragment
    private fun openCreateUserFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CreateNewUserFragment()).commit()
    }
}