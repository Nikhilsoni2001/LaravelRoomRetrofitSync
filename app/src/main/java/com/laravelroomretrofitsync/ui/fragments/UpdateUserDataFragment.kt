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
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.models.User
import com.laravelroomretrofitsync.ui.UserActivity
import com.laravelroomretrofitsync.ui.UserViewModel
import com.laravelroomretrofitsync.util.Resource

class UpdateUserDataFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etMobile: TextInputEditText
    private lateinit var btnClear: MaterialButton
    private lateinit var btnUpdate: MaterialButton
    private lateinit var llLoaing: LinearLayout
    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_user_data, container, false)

        viewModel = (activity as UserActivity).viewModel

        initialize(view)
        hideProgressBar()

        // getting item id from user click
        id = arguments?.getInt("id")

        btnClear.setOnClickListener {
            etUsername.setText("")
            etEmail.setText("")
            etMobile.setText("")
        }

        btnUpdate.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            updateUser(username, email, mobile, id)
        }

        return view
    }

    private fun updateUser(username: String, email: String, mobile: String, id: Int?) {
        val updateUser = User(id, email, mobile, "Inactive", username)
        if (id != null) {
            viewModel.updateUser(id, updateUser)
        }
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { response ->

            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    openUserFragment()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {message ->
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
    }

    private fun openUserFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserFragment())
            .commit()
    }

    private fun initialize(view: View) {
        etUsername = view.findViewById(R.id.etUserNameUpdate)
        etEmail = view.findViewById(R.id.etEmailUpdate)
        etMobile = view.findViewById(R.id.etPhoneUpdate)
        btnClear = view.findViewById(R.id.btnClearUpdate)
        btnUpdate = view.findViewById(R.id.btnUpdate)
        llLoaing = view.findViewById(R.id.llLoading)
    }

    private fun hideProgressBar() {
        llLoaing.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        llLoaing.visibility = View.VISIBLE
    }
}