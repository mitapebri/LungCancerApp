package com.example.lungcancerapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lungcancerapp.R

class ModelSectionAdapter(private val sectionList: List<ModelFragment.Section>) :
    RecyclerView.Adapter<ModelSectionAdapter.SectionViewHolder>() {

    inner class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sectionTitle: TextView = view.findViewById(R.id.sectionTitle)
        val sectionItemsContainer: ViewGroup = view.findViewById(R.id.sectionItemsContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_section, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sectionList[position]
        holder.sectionTitle.text = section.sectionTitle

        holder.sectionItemsContainer.removeAllViews()
        for (item in section.items) {
            val itemView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.item_section_item, holder.sectionItemsContainer, false)
            val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
            val itemDescription = itemView.findViewById<TextView>(R.id.itemDescription)
            val itemImage = itemView.findViewById<ImageView>(R.id.itemImage)

            itemTitle.text = item.title
            itemDescription.text = item.description
            itemImage.setImageResource(item.imageResId)

            holder.sectionItemsContainer.addView(itemView)
        }
    }

    override fun getItemCount(): Int = sectionList.size
}
