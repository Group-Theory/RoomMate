package com.grouptheory.roommate.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Complaint
import com.grouptheory.roommate.R

// TODO: Change to alerts, which should be the superclass of both complaints and chores
class ComplaintsListAdapter : ListAdapter<Complaint, ComplaintsListAdapter.ComplaintViewHolder>(AlertsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        return ComplaintViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val current = getItem(position)
        // TODO
        holder.bind(current.id.toString())
    }

    class ComplaintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: Other fields
        private val alertItemView: TextView

        init {
            alertItemView = itemView.findViewById(R.id.assignedUser)
        }

        fun bind(assignedUser: String) {
            alertItemView.text = assignedUser
        }

        companion object {
            fun create(parent: ViewGroup): ComplaintViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_alerts, parent, false)

                return ComplaintViewHolder(view)
            }
        }
    }

    class AlertsComparator : DiffUtil.ItemCallback<Complaint>() {

        override fun areContentsTheSame(oldItem: Complaint, newItem: Complaint): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Complaint, newItem: Complaint): Boolean {
            return oldItem === oldItem
        }
    }
}
