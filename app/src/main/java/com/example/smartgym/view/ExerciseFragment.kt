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
import com.example.smartgym.viewModel.ExerciseViewModel
import com.example.smartgym.MainActivity
import com.example.smartgym.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.exercise_fragment.*

class ExerciseFragment : Fragment() {

    private lateinit var viewModel: ExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.exercise_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recylerViewList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModel.postListAdapter

        view.findViewById<ImageButton>(R.id.buttonHome).setOnClickListener {
            startMain()
        }

        view.findViewById<ImageButton>(R.id.buttonWorkout).setOnClickListener {
            startWorkout()
        }

        view.findViewById<FloatingActionButton>(R.id.buttonRegister).setOnClickListener{
            startRegister()
//            findNavController().navigate(R.id.exerciseRegisterFragment)
        }


        return view

    }

    private fun startMain(){
        val i = Intent(context, MainActivity::class.java)
        startActivity(i)
    }


    private fun startRegister(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.exerciseFragment, ExerciseRegisterFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        recylerViewList.visibility = View.GONE
    }

    private fun startWorkout(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.exerciseFragment, WorkoutFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        recylerViewList.visibility = View.GONE
        buttonRegister.visibility = View.GONE

    }


}