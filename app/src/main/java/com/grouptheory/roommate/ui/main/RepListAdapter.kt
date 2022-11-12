package com.grouptheory.roommate.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.R

class RepListAdapter : ListAdapter<Rep, RepListAdapter.RepViewHolder>(AlertsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepViewHolder {
        return RepViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepViewHolder, position: Int) {
        val current = getItem(position)
        // TODO
        holder.bind(current.points)
    }

    class RepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: Other fields
        private val isComplaint: TextView
        private val complaintBackgroundColor = ContextCompat.getColor(
            itemView.context,
            R.color.is_complaint
        )
        private val complaintTextColor = ContextCompat.getColor(
            itemView.context,
            R.color.white
        )

        init {
            isComplaint = itemView.findViewById(R.id.is_complaint)
        }

        fun bind(points: Int) {
            if (points < 0) {
                isComplaint.setBackgroundColor(complaintBackgroundColor)
                isComplaint.setTextColor(complaintTextColor )
                isComplaint.text = "Complaint!"
            }
            else {
                isComplaint.text = "Good work!"
            }
        }

        companion object {
            fun create(parent: ViewGroup): RepViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_rep, parent, false)

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
