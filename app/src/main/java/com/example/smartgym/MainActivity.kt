package com.example.smartgym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smartgym.repository.FirebaseRepo
import com.example.smartgym.view.ExerciseFragment
import com.example.smartgym.view.LoginFragment
import com.example.smartgym.view.WorkoutFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (firebaseRepo.getUser() == null)  callLoginFragment()

        setupButtonFunctions()

    }

    private fun setupButtonFunctions(){
        buttonExercises.setOnClickListener{
            callExerciseFragment()
        }
        buttonWorkout.setOnClickListener{
            callWorkoutFragment()
        }
    }

    private fun callLoginFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layoutMain, LoginFragment())
            .commit()
    }

    private fun callExerciseFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layoutMain, ExerciseFragment())
            .commit()
        containerPhrases.visibility = View.GONE
        containerDicas.visibility = View.GONE

    }

    private fun callWorkoutFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layoutMain, WorkoutFragment())
            .commit()
        containerPhrases.visibility = View.GONE
        containerDicas.visibility = View.GONE
    }
}