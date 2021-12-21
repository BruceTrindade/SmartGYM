package com.example.smartgym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExercisesListAdapter(var postListItems: List<Exercises>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class DescViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(exercises: Exercises){
            val name = itemView.findViewById<TextView>(R.id.textName)
            name.text = exercises.name
            itemView.findViewById<TextView>(R.id.textDescription).text = exercises.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_layout_row, parent, false)
        return DescViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DescViewHolder).bind(postListItems[position])
    }

    override fun getItemCount(): Int {
        return postListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}