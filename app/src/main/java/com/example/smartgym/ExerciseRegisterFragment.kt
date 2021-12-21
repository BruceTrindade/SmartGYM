package com.example.smartgym

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.smartgym.R.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.exercise_fragment.*
import kotlinx.android.synthetic.main.exercise_fragment.recylerViewList
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

}