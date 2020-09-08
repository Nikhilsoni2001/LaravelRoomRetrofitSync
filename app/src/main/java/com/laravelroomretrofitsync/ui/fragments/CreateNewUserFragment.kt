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

class CreateNewUserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etMobile: TextInputEditText
    private lateinit var btnClear: MaterialButton
    private lateinit var btnSave: MaterialButton
    private lateinit var llLoaing: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_new_user, container, false)

        viewModel = (activity as UserActivity).viewModel

        initialization(view)
        hideProgressBar()

        btnClear.setOnClickListener {
            etUsername.setText("")
            etEmail.setText("")
            etMobile.setText("")
        }

        btnSave.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            createNewUser(username, email, mobile)
        }
        return view
    }

    private fun initialization(view: View) {
        etUsername = view.findViewById(R.id.etUserNameCreate)
        etEmail = view.findViewById(R.id.etEmailCreate)
        etMobile = view.findViewById(R.id.etPhoneCreate)
        btnClear = view.findViewById(R.id.btnClearCreate)
        btnSave = view.findViewById(R.id.btnSave)
        llLoaing = view.findViewById(R.id.llLoading)
    }

    private fun createNewUser(username: String, email: String, mobile: String) {
        val createUser = User(0, email, mobile, "Active", username)
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { response ->
            viewModel.createUser(createUser)

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    openUserFragment()
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
    }

    private fun openUserFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserFragment())
            .commit()
    }

    private fun hideProgressBar() {
        llLoaing.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        llLoaing.visibility = View.VISIBLE
    }

}