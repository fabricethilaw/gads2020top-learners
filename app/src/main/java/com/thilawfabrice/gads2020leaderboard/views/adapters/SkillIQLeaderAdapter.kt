package com.thilawfabrice.gads2020leaderboard.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thilawfabrice.gads2020leaderboard.R
import com.thilawfabrice.gads2020leaderboard.viewmodels.ListItemData

class SkillIQLeaderAdapter(private val data: MutableList<ListItemData> = mutableListOf()) :
    RecyclerView.Adapter<SkillIQLeaderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_item_still_iq_leaders, parent, false)
        return ViewHolder(viewItem)
    }

    fun update(updatedData: List<ListItemData>) {
        data.clear()
        data.addAll(updatedData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(itemData: ListItemData) {
            val iconImageView = itemView.findViewById(R.id.icon) as ImageView
            val nameTextView = itemView.findViewById(R.id.name) as TextView
            val detailsTextView = itemView.findViewById(R.id.details) as TextView

            iconImageView.setImageResource(R.drawable.top_learner)
            with(itemData) {
                nameTextView.text = name
                detailsTextView.text = details
                //todo  iconImageView

            }


        }
    }

}
