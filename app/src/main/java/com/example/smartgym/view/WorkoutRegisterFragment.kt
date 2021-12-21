package com.example.smartgym.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import com.example.smartgym.MainActivity
import com.example.smartgym.R
import com.example.smartgym.viewModel.WorkoutRegisterViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.workout_register_fragment.*

class WorkoutRegisterFragment : Fragment() {

    private lateinit var viewModel: WorkoutRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.workout_register_fragment, container, false)
        viewModel = ViewModelProvider(this).get(WorkoutRegisterViewModel::class.java)

        viewModel.msg.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank())
                Snackbar
                    .make(
                        view,
                        it,
                        Snackbar.LENGTH_LONG
                    ).show()
        })

        viewModel.status.observe(viewLifecycleOwner, {
            if (it)
                startWorkout()
        })

        return  view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddWorkout.setOnClickListener{
            val name = view.findViewById<EditText>(R.id.textNameWorkoutRegister).text.toString()
            val description = view.findViewById<EditText>(R.id.textDescriptionWorkotRegister).text.toString()
            viewModel.addWorkout(name, description)
        }

        view.findViewById<ImageButton>(R.id.buttonWorkout).setOnClickListener{
            startWorkout()
        }

        view.findViewById<ImageButton>(R.id.buttonHome).setOnClickListener{
            startMain()
        }

        view.findViewById<ImageButton>(R.id.buttonExercises).setOnClickListener{
            startExercise()
        }

    }

    private fun startWorkout(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.workoutRegisterFragment, WorkoutFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        titleRegisterWorkout.visibility = View.GONE
        textNameWorkoutRegister.visibility = View.GONE
        textDescriptionWorkotRegister.visibility = View.GONE
        buttonAddWorkout.visibility = View.GONE

    }

    private fun startExercise(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.workoutRegisterFragment, ExerciseFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        titleRegisterWorkout.visibility = View.GONE
        textNameWorkoutRegister.visibility = View.GONE
        textDescriptionWorkotRegister.visibility = View.GONE
        buttonAddWorkout.visibility = View.GONE

    }

    private fun startMain(){
        val i = Intent(context, MainActivity::class.java)
        startActivity(i)
        titleRegisterWorkout.visibility = View.GONE
        textNameWorkoutRegister.visibility = View.GONE
        textDescriptionWorkotRegister.visibility = View.GONE
        buttonAddWorkout.visibility = View.GONE
    }

}