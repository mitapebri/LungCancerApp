package com.example.lungcancerapp.ui.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lungcancerapp.R

class ModelDetailAdapter(private val items: List<ModelItem>) :
    RecyclerView.Adapter<ModelDetailAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.itemTitle)
        val description: TextView = view.findViewById(R.id.itemDescription)
        val image: ImageView = view.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_model_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.description.text = item.description
        if (item.imageResId != null) {
            holder.image.setImageResource(item.imageResId)
            holder.image.visibility = View.VISIBLE
        } else {
            holder.image.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
}
