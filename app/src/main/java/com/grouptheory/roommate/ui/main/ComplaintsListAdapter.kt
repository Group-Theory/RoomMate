package com.grouptheory.roommate.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.R

// TODO: Change to alerts, which should be the superclass of both complaints and chores
class RepsListAdapter : ListAdapter<Rep, RepsListAdapter.RepViewHolder>(AlertsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepViewHolder {
        return RepViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepViewHolder, position: Int) {
        val current = getItem(position)
        // TODO
        holder.bind(current.id.toString())
    }

    class RepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: Other fields
        private val alertItemView: TextView

        init {
            alertItemView = itemView.findViewById(R.id.assignedUser)
        }

        fun bind(assignedUser: String) {
            alertItemView.text = assignedUser
        }

        companion object {
            fun create(parent: ViewGroup): RepViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_alerts, parent, false)

                return RepViewHolder(view)
            }
        }
    }

    class AlertsComparator : DiffUtil.ItemCallback<Rep>() {

        override fun areContentsTheSame(oldItem: Rep, newItem: Rep): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Rep, newItem: Rep): Boolean {
            return oldItem === oldItem
        }
    }
}
