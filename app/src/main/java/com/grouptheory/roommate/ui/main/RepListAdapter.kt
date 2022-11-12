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
        holder.bind(current.points, current.description)
    }

    class RepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Individual text views
        private val isComplaintView: TextView
        private val descriptionView: TextView
        private val scoreView: TextView

        // Resources for changing the look depending on score
        private val complaintBackgroundColor = ContextCompat.getColor(
            itemView.context,
            R.color.is_complaint
        )
        private val complaintTextColor = ContextCompat.getColor(
            itemView.context,
            R.color.white
        )
        private val commendationBackgroundColor = ContextCompat.getColor(
            itemView.context,
            R.color.cream
        )
        private val commendationTextColor = ContextCompat.getColor(
            itemView.context,
            R.color.black
        )

        private val scoreString = itemView.context.getString(R.string.score_template)

        init {
            isComplaintView = itemView.findViewById(R.id.is_complaint)
            descriptionView = itemView.findViewById(R.id.description)
            scoreView = itemView.findViewById(R.id.score)
        }

        fun bind(score: Int, description: String) {
            // Change look of title whether this is a complaint or commendation
            if (score < 0) {
                isComplaintView.setBackgroundColor(complaintBackgroundColor)
                isComplaintView.setTextColor(complaintTextColor )
                isComplaintView.text = "Complaint!"
            }
            else {
                isComplaintView.setBackgroundColor(commendationBackgroundColor)
                isComplaintView.setTextColor(commendationTextColor)
                isComplaintView.text = "Praise!"
            }

            // Set the description and score value
            descriptionView.text = description
            scoreView.text = String.format(scoreString, score)
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
