package com.dvoronov00.rentateamtest.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dvoronov00.rentateamtest.R
import com.dvoronov00.rentateamtest.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val items = arrayListOf<User>()
    private var listener : UserClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: User, listener: UserClickListener?) {
            val nameView = itemView.findViewById<TextView>(R.id.textViewName)

            nameView.text = "${item.first_name} ${item.last_name}"

            itemView.setOnClickListener {
                listener?.onClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setItems(items: List<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: UserClickListener){
        this.listener = listener
    }

}
