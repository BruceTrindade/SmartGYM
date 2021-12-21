package com.example.smartgym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgym.repository.Exercises
import com.example.smartgym.repository.Workout


class WorkoutListAdapter(var postWorkoutListItems: List<Workout>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class DescViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(workout: Workout){
            val name = itemView.findViewById<TextView>(R.id.textNameWorkout)
            name.text = workout.nameWorkout
            itemView.findViewById<TextView>(R.id.textDescriptionWorkout).text = workout.descriptionWorkout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout_layout_row, parent, false)
        return DescViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DescViewHolder).bind(postWorkoutListItems[position])
    }

    override fun getItemCount(): Int {
        return postWorkoutListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}