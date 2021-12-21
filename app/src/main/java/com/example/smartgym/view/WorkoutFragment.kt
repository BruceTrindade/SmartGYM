package com.example.smartgym.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgym.MainActivity
import com.example.smartgym.R
import com.example.smartgym.viewModel.WorkoutViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.workout_fragment.*

class WorkoutFragment : Fragment() {

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.workout_fragment, container, false)
        viewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recylerViewWorkoutList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModel.postWorkoutListAdapter

        view.findViewById<ImageButton>(R.id.buttonHomeWorkout).setOnClickListener {
            startMain()
        }

        view.findViewById<FloatingActionButton>(R.id.buttonRegisterWorkout).setOnClickListener{
            startRegister()
        }

        view.findViewById<ImageButton>(R.id.buttonExercisesWorkout).setOnClickListener{
            startExercise()
        }

        return view
    }

    private fun startRegister(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.workoutFragment, WorkoutRegisterFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        recylerViewWorkoutList.visibility = View.GONE
    }

    private fun startExercise(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.workoutFragment, ExerciseFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        recylerViewWorkoutList.visibility = View.GONE
        buttonRegisterWorkout.visibility = View.GONE

    }

    private fun startMain(){
        val i = Intent(context, MainActivity::class.java)
        startActivity(i)
    }

}