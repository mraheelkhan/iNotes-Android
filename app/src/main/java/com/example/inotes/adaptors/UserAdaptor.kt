package com.example.inotes.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.inotes.R
import com.example.inotes.model.UserModel
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.user_list_layout.view.*

class UserAdaptor (val users : List<UserModel>): RecyclerView.Adapter<UserAdaptor.UserViewHolder>(){

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_layout, parent, false)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.view.text_view_user_note.text = users[position].name
        holder.view.text_view_user_title.text = users[position].store.toString()


    }


}