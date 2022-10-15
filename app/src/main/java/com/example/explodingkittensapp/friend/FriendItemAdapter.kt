package com.example.explodingkittensapp.friend

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.explodingkittensapp.R
import com.example.explodingkittensapp.model.Friend

class FriendItemAdapter(
    private val friends: MutableList<Friend>,
    private val actionListener: ActionListener
) : RecyclerView.Adapter<FriendItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val friendName = itemView.findViewById<TextView>(R.id.userName)!!
        val friendItem = itemView.findViewById<ViewGroup>(R.id.friendItem)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context

        val inflater = LayoutInflater.from(context)

        val friendView: View = inflater.inflate(R.layout.friend_item, parent, false)

        return ViewHolder(friendView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend: Friend = friends[position]

        val name = holder.friendName
        val detailsButton = holder.friendItem

        name.text = friend.username


        detailsButton.setOnClickListener {
            actionListener.goToFriendDetails(friend)
        }
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFriends(newFriends: List<Friend>) {
        friends.clear()

        friends.addAll(newFriends)
        notifyDataSetChanged()
    }

    interface ActionListener {
        fun goToFriendDetails(friend: Friend)
    }
}