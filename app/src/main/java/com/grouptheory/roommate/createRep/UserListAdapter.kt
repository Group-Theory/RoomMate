package com.grouptheory.roommate.createRep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.DataClasses.User
import com.grouptheory.roommate.R

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.userName, current.score)
    }

    class UserViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Individual text views
        private val usernameView: TextView = itemView.findViewById(R.id.username)
        private val scoreView: TextView = itemView.findViewById(R.id.roommateScore)

        fun bind(username: String, score: Long) {
            usernameView.text = username
            itemView.tag = username

            val scoreTemplate = itemView.context.getString(R.string.score_template)
            scoreView.text = String.format(scoreTemplate, score)
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_roommates, parent, false)

                return UserViewHolder(view)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<User>() {

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {

            return oldItem.userName == newItem.userName
        }

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === oldItem
        }
    }
}
