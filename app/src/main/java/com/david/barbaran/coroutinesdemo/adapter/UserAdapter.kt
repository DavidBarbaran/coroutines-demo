package com.david.barbaran.coroutinesdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.barbaran.coroutinesdemo.R
import com.david.barbaran.coroutinesdemo.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    lateinit var list: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return if (::list.isInitialized) list.size else 0
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = list[holder.adapterPosition]
        holder.itemView.nameText.text = user.name
        holder.itemView.lastNameText.text = user.lastname
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}