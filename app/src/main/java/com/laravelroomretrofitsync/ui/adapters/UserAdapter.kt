package com.laravelroomretrofitsync.ui.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.models.User
import com.laravelroomretrofitsync.ui.UserActivity
import com.laravelroomretrofitsync.ui.fragments.UpdateUserDataFragment
import java.util.ArrayList

class UserAdapter(val context: Context, private var list: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.user_id)
        val userName: TextView = view.findViewById(R.id.user_name)
        val userPhone: TextView = view.findViewById(R.id.user_phone)
        val userEmail: TextView = view.findViewById(R.id.user_email)
        val userStatus: TextView = view.findViewById(R.id.user_status)
        val user: RelativeLayout = view.findViewById(R.id.user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_user_single_row, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]

        holder.id.text = item.id.toString()
        holder.userName.text = item.user_name
        holder.userPhone.text = item.user_phone
        holder.userEmail.text = item.user_email
        holder.userStatus.text = item.user_status

        holder.user.setOnClickListener {

            val fragment = UpdateUserDataFragment()
            val args = Bundle()

            item.id?.let { id ->
                args.putInt("id", id)
            }
            fragment.arguments = args

            (context as UserActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}