package com.example.smartgym.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.smartgym.viewModel.ExerciseRegisterViewModel
import com.example.smartgym.R
import com.example.smartgym.R.*
import com.example.smartgym.WorkoutFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.exercise_register_fragment.*

class ExerciseRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseRegisterFragment()
    }

    private lateinit var viewModel: ExerciseRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.exercise_register_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ExerciseRegisterViewModel::class.java)

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
         //   findNavController().popBackStack()
                startExercise()
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       buttonAdd.setOnClickListener{
            val name = view.findViewById<EditText>(R.id.textName).text.toString()
            val description = view.findViewById<EditText>(R.id.textDescription).text.toString()
            viewModel.addExercises(name, description)
        }

        buttonWorkout.setOnClickListener {
            startWorkout()
        }

        buttonExercises.setOnClickListener {
            startExercise()
        }



    }

    private fun startExercise(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.exerciseRegisterFragment, ExerciseFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        textDescription.visibility = View.GONE
        textName.visibility = View.GONE
        buttonAdd.visibility = View.GONE

    }

    private fun startWorkout(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.exerciseRegisterFragment, WorkoutFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        textDescription.visibility = View.GONE
        textName.visibility = View.GONE
        buttonAdd.visibility = View.GONE
    }


}